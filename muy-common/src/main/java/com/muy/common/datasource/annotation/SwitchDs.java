package com.muy.common.datasource.annotation;

import java.lang.annotation.*;

/**
 * 切换数据源
 *
 * @author by yanglikai on 16/08/17.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
public @interface SwitchDs {
    DbMsEnum ms() default DbMsEnum.Master;
}
