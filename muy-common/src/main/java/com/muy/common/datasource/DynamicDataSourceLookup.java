package com.muy.common.datasource;

import org.springframework.jdbc.datasource.lookup.DataSourceLookup;
import org.springframework.jdbc.datasource.lookup.DataSourceLookupFailureException;

import javax.sql.DataSource;

/**
 * Created by yanglikai on 2017/1/7.
 */
public class DynamicDataSourceLookup implements DataSourceLookup {
    private final DynamicDataSource dataSource;

    public DynamicDataSourceLookup(DynamicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public DataSource getDataSource(String dataSourceName) throws DataSourceLookupFailureException {
        return dataSource;
    }
}
