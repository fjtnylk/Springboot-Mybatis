package com.muy.common.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by yanglikai on 2017/1/6.
 */
@Configuration
@ComponentScan(value = "com.muy.common")
@Import({DatasourceConfig.class, MybatisConfig.class})
public class MuyConfig {
}
