package com.muy.common.datasource.annotation;

import java.lang.annotation.*;

/**
 * 分库时会使用某字段进行分库, 此系统暂无分库, 暂不起作用
 *
 * @author by yanglikai on 16/08/17.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Inherited
public @interface SwitchField {
    String field() default "";
}
