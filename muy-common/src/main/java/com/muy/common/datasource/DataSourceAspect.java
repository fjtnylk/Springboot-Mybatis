package com.muy.common.datasource;

import com.muy.common.datasource.annotation.SwitchDs;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 数据源切面，拦截指定方法
 *
 * @author by yanglikai on 16/08/17.
 */
@Aspect
@Component
@Order(0)
public class DataSourceAspect {
    private static final Logger log = Logger.getLogger(DataSourceAspect.class);

    @Before("@annotation(ds)")
    public void before(JoinPoint point, SwitchDs ds) throws Throwable {
        if (log.isDebugEnabled()) {
            log.debug(String.format("switch ds is %s", ds.ms().get()));
        }
        DynamicDataSourceHolder.putDataSource(ds.ms().get());
    }
}
