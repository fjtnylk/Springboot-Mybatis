package com.muy.common.datasource.table.strategy.impl;

import com.muy.common.datasource.table.strategy.AbstractShardStrategy;
import com.muy.common.utils.ReflectUtils;
import org.apache.log4j.Logger;

import java.util.Map;

/**
 * Created by yanglikai on 16/08/17.
 */
public class YmStrategyRule extends AbstractShardStrategy {
    private static final Logger log = Logger.getLogger(YmStrategyRule.class);
    @Override
    protected void init() {

    }

    @Override
    public String getTargetTableName(String baseTableName, Object params, String mapperId) {
        Object keyObj = null;
        if (params instanceof Map) {
            keyObj = ((Map) params).get(key);
        } else {
            keyObj = ReflectUtils.getFieldValue(params, key);
        }
        return baseTableName + keyObj.toString();
    }
}
