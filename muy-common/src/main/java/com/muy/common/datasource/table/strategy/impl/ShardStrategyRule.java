package com.muy.common.datasource.table.strategy.impl;

import com.muy.common.datasource.table.strategy.AbstractShardStrategy;
import com.muy.common.utils.ReflectUtils;
import com.muy.common.utils.ScriptEngineUtils;
import org.apache.log4j.Logger;

import javax.script.ScriptEngine;
import javax.script.ScriptException;
import java.util.Map;

/**
 * description
 *
 * @author by yanglikai on 16/08/17.
 */
public class ShardStrategyRule extends AbstractShardStrategy {
    private static final Logger log = Logger.getLogger(ShardStrategyRule.class);

    private String s = "y = $expression; return y";

    @Override
    protected void init() {
        this.s = s.replace("$expression", expression);
    }

    /**
     *
     * @param baseTableName 逻辑表名，一般是没有前缀或后缀的表名
     * @param params mapper内的参数  当多个时，是map集合，对象为object
     * @param mapperId statement.id
     * @return
     */
    public String getTargetTableName(String baseTableName, Object params, String mapperId) {
        try {
            Object keyObj = null;
            if (params instanceof Map) {
                keyObj = ((Map)params).get(key);
            } else {
                keyObj = ReflectUtils.getFieldValue(params, key);
            }

            if (keyObj != null) {
                ScriptEngine engine = ScriptEngineUtils.getEngineInstance();
                engine.put(key, keyObj);
                Object obj = engine.eval(s);
                return baseTableName + obj;
            }
        } catch (ScriptException e) {
            log.error(String.format("%s express eval error: %s back default table name", s, e.getMessage()));
        }
        return baseTableName;
    }
}
