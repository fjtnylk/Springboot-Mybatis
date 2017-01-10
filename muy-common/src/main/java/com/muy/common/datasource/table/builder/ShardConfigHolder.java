package com.muy.common.datasource.table.builder;

import com.muy.common.datasource.table.strategy.AbstractShardStrategy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author by yanglikai on 16/08/17.
 */
public class ShardConfigHolder {
    private static final ShardConfigHolder instance = new ShardConfigHolder();
    public static ShardConfigHolder getInstance(){
        return instance;
    }

    private Map<String, AbstractShardStrategy> strategyRegister = new HashMap<String, AbstractShardStrategy>();

    private Set<String> ignoreSet;
    private Set<String> parseSet;

    private ShardConfigHolder(){

    }

    /**
     * 注册分表策略
     * @param table
     * @param stratety
     */
    public void register(String table, AbstractShardStrategy stratety) {
        this.strategyRegister.put(table.toLowerCase(), stratety);
    }

    /**
     * 查找对应分表策略
     * @param table
     * @return
     */
    public AbstractShardStrategy getStrategy(String table) {
        return strategyRegister.get(table.toLowerCase());
    }

    /**
     * 增加ignore id 配置
     * @param id
     */
    public synchronized void addIgnoreId(String id) {
        if (ignoreSet == null) {
            ignoreSet = new HashSet<String>();
        }
        ignoreSet.add(id);
    }

    /**
     * 增加parse id 配置
     *
     * @param id
     */
    public synchronized void addParseId(String id) {
        if (parseSet == null) {
            parseSet = new HashSet<String>();
        }
        parseSet.add(id);
    }

    public boolean isConfigParseId(){
        return parseSet != null;
    }

    public boolean isParseId(String id) {
        return parseSet != null && parseSet.contains(id);
    }

    public boolean isIgnoreId(String id) {
        return ignoreSet != null && ignoreSet.contains(id);
    }
}
