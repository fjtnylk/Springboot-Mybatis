package com.muy.bo.config;

import com.muy.common.config.MuyConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by yanglikai on 2017/1/6.
 */
@Configuration
@Import(MuyConfig.class)
public class BackofficeConfig {
}
