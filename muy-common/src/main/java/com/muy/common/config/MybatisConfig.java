package com.muy.common.config;

import com.github.pagehelper.PageInterceptor;
import com.muy.common.datasource.table.TableInterceptor;
import com.muy.common.utils.MuyMapper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by yanglikai on 2017/1/6.
 */
@Configuration
@ConditionalOnClass({EnableTransactionManagement.class, EntityManager.class})
@AutoConfigureAfter({DatasourceConfig.class})
@MapperScan(basePackages = {"com.muy.common.mapper"}, sqlSessionFactoryRef = "sqlSessionFactory", markerInterface = MuyMapper.class)
public class MybatisConfig {
    @Resource
    private DataSource dataSource;

    @Bean(name = "sqlSessionFactory")
    @ConditionalOnMissingBean
    public SqlSessionFactory sqlSessionFactory() {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setTypeAliasesPackage("com.muy.common.entity");

        // 分页插件
        PageInterceptor pageInterceptor = new PageInterceptor();
        Properties properties = new Properties();
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("returnPageInfo", "check");
        properties.setProperty("params", "count=countSql");
        pageInterceptor.setProperties(properties);

        // 分表插件
        TableInterceptor tableInterceptor = new TableInterceptor();
        Properties tableProperties = new Properties();
        tableProperties.setProperty("shardingConfig", "/dtd/shardtable-config.xml");
        tableInterceptor.setProperties(tableProperties);

        // 添加插件
        sqlSessionFactoryBean.setPlugins(new Interceptor[]{pageInterceptor, tableInterceptor});

        // 添加Mybatis自定义配置
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        sqlSessionFactoryBean.setConfiguration(configuration);

        // 添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:mapper/*/*Mapper.xml"));
            return sqlSessionFactoryBean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Bean
    @ConditionalOnMissingBean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }
}
