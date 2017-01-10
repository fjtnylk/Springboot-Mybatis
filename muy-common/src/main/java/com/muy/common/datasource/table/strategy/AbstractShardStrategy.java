package com.muy.common.datasource.table.strategy;

/**
 * @author by yanglikai on 16/08/17.
 */
public abstract class AbstractShardStrategy {
    protected String key;
    protected String expression;

    protected abstract void init();

    /**
     * 实际表名
     * @param baseTableName 逻辑表名，一般是没有前缀或后缀的表名
     * @param params mapper内的参数  当多个时，是map集合，对象为object
     * @param mapperId statement.id
     * @return
     */
    public abstract String getTargetTableName(String baseTableName, Object params, String mapperId);
}
