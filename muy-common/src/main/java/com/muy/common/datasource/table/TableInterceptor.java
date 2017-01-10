package com.muy.common.datasource.table;

import com.muy.common.datasource.table.builder.ShardConfigHolder;
import com.muy.common.datasource.table.builder.ShardConfigParser;
import com.muy.common.datasource.table.converter.SqlConverterFactory;
import com.muy.common.utils.ReflectUtils;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * mybatis分表插件
 *
 * @author by yanglikai on 16/08/17.
 */
@Intercepts(@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class}))
public class TableInterceptor implements Interceptor {
    private static final Logger log = Logger.getLogger(TableInterceptor.class);

    private static final String SHARDING_CONFIG = "shardingConfig";

    private static final ConcurrentHashMap<String, Boolean> mapperCache = new ConcurrentHashMap<String, Boolean>();

    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        MappedStatement mappedStatement = null;
        if (statementHandler instanceof RoutingStatementHandler) {
            StatementHandler delegate = (StatementHandler) ReflectUtils.getFieldValue(statementHandler, "delegate");
            mappedStatement = (MappedStatement) ReflectUtils.getFieldValue(delegate, "mappedStatement");
        } else {
            mappedStatement = (MappedStatement) ReflectUtils.getFieldValue(statementHandler, "mappedStatement");
        }

        String mapperId = mappedStatement.getId();
        if (isShouldParse(mapperId)) {
            String sql = statementHandler.getBoundSql().getSql();
            if (log.isDebugEnabled()) {
                log.debug(String.format("Original Sql [%s]:%s", mapperId, sql));
            }
            Object params = statementHandler.getBoundSql().getParameterObject();
            if (log.isDebugEnabled()) {
                log.debug(String.format("mapper parmas is %s", params));
            }

            SqlConverterFactory cf = SqlConverterFactory.getInstance();
            sql = cf.convert(sql, params, mapperId);
            if (log.isDebugEnabled()) {
                log.debug(String.format("Converted Sql [%s] : %s", mapperId, sql));
            }
            ReflectUtils.setFieldValue(statementHandler.getBoundSql(), "sql", sql);
        }
        return invocation.proceed();
    }

    private boolean isShouldParse(String mapperId) {
        Boolean parse = mapperCache.get(mapperId);
        if (parse != null) { // 已被缓存
            return parse;
        }
        /*
         * 1.<selectKey>不做解析
		 * 2.在ignoreList里的sql不用处理
		 * 3.如果不在ignoreList里并且没有配置parseList则进行处理
		 * 4.如果不在ignoreList里并且也在parseList里则进行处理
		 * 5.如果不在ignoreList里并且也不在parseList里则不进行处理
		 */
        if (!mapperId.endsWith("!selectKey")) {
            ShardConfigHolder configHolder = ShardConfigHolder.getInstance();
            if (!configHolder.isIgnoreId(mapperId)) {
                if (!configHolder.isConfigParseId() || configHolder.isParseId(mapperId)) {
                    parse = true;
                }
            }
        }
        if (parse == null) {
            parse = false;
        }
        mapperCache.put(mapperId, parse);
        return parse;
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof StatementHandler) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }

    @Override
    public void setProperties(Properties properties) {

        String config = properties.getProperty(SHARDING_CONFIG, null);
        if (config == null || config.trim().length() == 0) {
            throw new IllegalArgumentException("property 'shardingConfig' is requested.");
        }
        ShardConfigParser parser = new ShardConfigParser();
        InputStream input = null;
        try {
//            input = new FileInputStream(new ClassPathResource(config).getFile());
            input = getClass().getResourceAsStream(config);
            parser.parse(input);
        } catch (IOException e) {
            log.error("Get sharding config file failed.");
            throw new IllegalArgumentException(e);
        } catch (Exception e) {
            log.error("parse sharding config file failed.");
            throw new IllegalArgumentException(e);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
            }
        }
    }
}
