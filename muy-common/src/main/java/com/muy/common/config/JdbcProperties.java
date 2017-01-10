package com.muy.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by yanglikai on 2017/1/6.
 */
@Configuration
@PropertySource("classpath:jdbc.properties")
public class JdbcProperties {
    @Value("${write.url}")
    private String writeUrl;
    @Value("${write.username}")
    private String writeUserName;
    @Value("${write.password}")
    private String writePassword;

    @Value("${read.url}")
    private String readUrl;
    @Value("${read.username}")
    private String readUserName;
    @Value("${read.password}")
    private String readPassword;

    @Value("${filters}")
    private String filters;
    @Value("${validationQuery}")
    private String validationQuery;
    @Value("${maxActive}")
    private Integer maxActive;
    @Value("${initialSize}")
    private Integer initialSize;
    @Value("${maxWait}")
    private Integer maxWait;
    @Value("${minIdle}")
    private Integer minIdle;
    @Value("${timeBetweenEvictionRunsMillis}")
    private Integer timeBetweenEvictionRunsMillis;
    @Value("${minEvictableIdleTimeMillis}")
    private Integer minEvictableIdleTimeMillis;
    @Value("${maxOpenPreparedStatements}")
    private Integer maxOpenPreparedStatements;
    @Value("${testWhileIdle}")
    private boolean testWhileIdle;
    @Value("${testOnBorrow}")
    private boolean testOnBorrow;
    @Value("${testOnReturn}")
    private boolean testOnReturn;
    @Value("${poolPreparedStatements}")
    private boolean poolPreparedStatements;

    public String getWriteUrl() {
        return writeUrl;
    }

    public String getWriteUserName() {
        return writeUserName;
    }

    public String getWritePassword() {
        return writePassword;
    }

    public String getReadUrl() {
        return readUrl;
    }

    public String getReadUserName() {
        return readUserName;
    }

    public String getReadPassword() {
        return readPassword;
    }

    public String getFilters() {
        return filters;
    }

    public String getValidationQuery() {
        return validationQuery;
    }

    public Integer getMaxActive() {
        return maxActive;
    }

    public Integer getInitialSize() {
        return initialSize;
    }

    public Integer getMaxWait() {
        return maxWait;
    }

    public Integer getMinIdle() {
        return minIdle;
    }

    public Integer getTimeBetweenEvictionRunsMillis() {
        return timeBetweenEvictionRunsMillis;
    }

    public Integer getMinEvictableIdleTimeMillis() {
        return minEvictableIdleTimeMillis;
    }

    public Integer getMaxOpenPreparedStatements() {
        return maxOpenPreparedStatements;
    }

    public boolean isTestWhileIdle() {
        return testWhileIdle;
    }

    public boolean isTestOnBorrow() {
        return testOnBorrow;
    }

    public boolean isTestOnReturn() {
        return testOnReturn;
    }

    public boolean isPoolPreparedStatements() {
        return poolPreparedStatements;
    }
}
