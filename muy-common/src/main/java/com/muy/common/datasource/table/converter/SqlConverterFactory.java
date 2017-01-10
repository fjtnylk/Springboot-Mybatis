package com.muy.common.datasource.table.converter;

import com.muy.common.datasource.table.converter.impl.DeleteSqlConverter;
import com.muy.common.datasource.table.converter.impl.InsertSqlConverter;
import com.muy.common.datasource.table.converter.impl.SelectSqlConverter;
import com.muy.common.datasource.table.converter.impl.UpdateSqlConverter;
import com.muy.common.datasource.table.ex.ShardException;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.update.Update;
import org.apache.log4j.Logger;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

/**
 * 管理各种curd语句
 *
 * @author by yanglikai on 16/08/17.
 */
public class SqlConverterFactory {
    private static final Logger log = Logger.getLogger(SqlConverterFactory.class);

    private static SqlConverterFactory factory;

    static {
        factory = new SqlConverterFactory();
    }

    public static SqlConverterFactory getInstance(){
        return factory;
    }

    private Map<String,SqlConverter> converterMap;

    private CCJSqlParserManager pm;

    private SqlConverterFactory(){
        converterMap = new HashMap<String, SqlConverter>();
        pm = new CCJSqlParserManager();
        register();
    }

    /**
     * 注册语法
     */
    private void register(){
        converterMap.put(Select.class.getName(), new SelectSqlConverter());
        converterMap.put(Delete.class.getName(), new DeleteSqlConverter());
        converterMap.put(Insert.class.getName(), new InsertSqlConverter());
        converterMap.put(Update.class.getName(), new UpdateSqlConverter());
    }

    public String convert(String sql, Object params, String mapperId) throws ShardException {
        Statement statement = null;
        try {
            statement = pm.parse(new StringReader(sql));
        } catch (JSQLParserException e) {
            log.error(String.format("sql convert is error:%s", e.getMessage()));
            throw new ShardException(e);
        }

        SqlConverter converter = this.converterMap.get(statement.getClass().getName());

        if (converter != null) {
            return converter.convert(statement, params, mapperId);
        }
        return sql;
    }
}
