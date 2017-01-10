package com.muy.common.datasource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

/**
 * 动态数据源，根据相应规则，可自主选择
 *
 * @author by yanglikai on 16/08/17.
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    private static final Logger log = Logger.getLogger(DynamicDataSource.class);

    private String defaultTargetDataSourceName;

    protected Object determineCurrentLookupKey() {
        Object obj = DynamicDataSourceHolder.getDataSource();
        if (log.isDebugEnabled()) {
            log.debug(String.format("get current datasource is %s", obj));
        }
        return obj;
    }

    public String getDefaultTargetDataSourceName() {
        return defaultTargetDataSourceName;
    }

    public void setDefaultTargetDataSourceName(String defaultTargetDataSourceName) {
        this.defaultTargetDataSourceName = defaultTargetDataSourceName;
    }
}
