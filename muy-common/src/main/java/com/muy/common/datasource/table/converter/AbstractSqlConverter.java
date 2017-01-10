package com.muy.common.datasource.table.converter;

import com.muy.common.datasource.table.builder.ShardConfigHolder;
import com.muy.common.datasource.table.strategy.AbstractShardStrategy;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.util.deparser.StatementDeParser;

/**
 * @author by yanglikai on 16/08/17.
 */
public abstract class AbstractSqlConverter implements SqlConverter {
    public String convert(Statement statement, Object params, String mapperId) {
        return doDeParse(doConvert(statement, params, mapperId));
    }

    /**
     * 将Statement反解析为sql
     * @param statement
     * @return
     */
    protected String doDeParse(Statement statement) {
        StatementDeParser deParser = new StatementDeParser(new StringBuilder());
        statement.accept(deParser);
        return deParser.getBuffer().toString();
    }

    /**
     * 从ShardConfigHolder中查找AbstractShardStrategy并对表名进行修改
     * 如果没有相应的strategy，则对表名不做修改
     * @param tableName
     * @param params
     * @param mapperId
     * @return
     */
    protected String convertTableName(String tableName, Object params, String mapperId) {
        ShardConfigHolder configHolder = ShardConfigHolder.getInstance();
        AbstractShardStrategy strategy = configHolder.getStrategy(tableName);
        if (strategy == null) {
            return tableName;
        }
        return strategy.getTargetTableName(tableName, params, mapperId);
    }

    /**
     * 修改statement代表的Sql语句
     * @param statement
     * @param params
     * @param mapperId
     * @return
     */
    protected abstract Statement doConvert(Statement statement, Object params, String mapperId);
}
