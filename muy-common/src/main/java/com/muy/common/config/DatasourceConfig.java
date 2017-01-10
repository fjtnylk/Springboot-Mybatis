package com.muy.common.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.muy.common.datasource.DynamicDataSource;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yanglikai on 2017/1/6.
 */
@Configuration
@AutoConfigureAfter({JdbcProperties.class})
@EnableTransactionManagement
public class DatasourceConfig {
    @Resource
    private JdbcProperties jdbcProperties;

    @Bean(name = "masterDs", initMethod = "init", destroyMethod = "close")
    @Primary
    public DruidDataSource masterDs() throws SQLException {
        DruidDataSource masterDs = new DruidDataSource();
        masterDs.setUrl(jdbcProperties.getWriteUrl());
        masterDs.setUsername(jdbcProperties.getWriteUserName());
        masterDs.setPassword(jdbcProperties.getWritePassword());

        masterDs.setFilters(jdbcProperties.getFilters());
        masterDs.setMaxActive(jdbcProperties.getMaxActive());
        masterDs.setInitialSize(jdbcProperties.getInitialSize());
        masterDs.setMaxWait(jdbcProperties.getMaxWait());
        masterDs.setMinIdle(jdbcProperties.getMinIdle());

        masterDs.setTimeBetweenEvictionRunsMillis(jdbcProperties.getTimeBetweenEvictionRunsMillis());
        masterDs.setMinEvictableIdleTimeMillis(jdbcProperties.getMinEvictableIdleTimeMillis());

        masterDs.setValidationQuery(jdbcProperties.getValidationQuery());
        masterDs.setTestWhileIdle(jdbcProperties.isTestWhileIdle());
        masterDs.setTestOnBorrow(jdbcProperties.isTestOnBorrow());
        masterDs.setTestOnReturn(jdbcProperties.isTestOnReturn());

        masterDs.setPoolPreparedStatements(jdbcProperties.isPoolPreparedStatements());
        masterDs.setMaxOpenPreparedStatements(jdbcProperties.getMaxOpenPreparedStatements());
        return masterDs;
    }

    @Bean(name = "slaveDs", initMethod = "init", destroyMethod = "close")
    public DruidDataSource slaveDs() throws SQLException {
        DruidDataSource slaveDs = new DruidDataSource();
        slaveDs.setUrl(jdbcProperties.getReadUrl());
        slaveDs.setUsername(jdbcProperties.getReadUserName());
        slaveDs.setPassword(jdbcProperties.getReadPassword());

        slaveDs.setFilters(jdbcProperties.getFilters());
        slaveDs.setMaxActive(jdbcProperties.getMaxActive());
        slaveDs.setInitialSize(jdbcProperties.getInitialSize());
        slaveDs.setMaxWait(jdbcProperties.getMaxWait());
        slaveDs.setMinIdle(jdbcProperties.getMinIdle());

        slaveDs.setTimeBetweenEvictionRunsMillis(jdbcProperties.getTimeBetweenEvictionRunsMillis());
        slaveDs.setMinEvictableIdleTimeMillis(jdbcProperties.getMinEvictableIdleTimeMillis());

        slaveDs.setValidationQuery(jdbcProperties.getValidationQuery());
        slaveDs.setTestWhileIdle(jdbcProperties.isTestWhileIdle());
        slaveDs.setTestOnBorrow(jdbcProperties.isTestOnBorrow());
        slaveDs.setTestOnReturn(jdbcProperties.isTestOnReturn());

        slaveDs.setPoolPreparedStatements(jdbcProperties.isPoolPreparedStatements());
        slaveDs.setMaxOpenPreparedStatements(jdbcProperties.getMaxOpenPreparedStatements());
        return slaveDs;
    }

    @Bean(name = "dataSource")
    public DynamicDataSource dataSource() throws SQLException {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setDefaultTargetDataSourceName("masterDs");

        Map<Object, Object> targets = new HashMap<>();
        targets.put("masterDs", masterDs());
        targets.put("slaveDs", slaveDs());
        dynamicDataSource.setTargetDataSources(targets);
        return dynamicDataSource;
    }
}
