package com.muy.common.datasource;

/**
 * 数据源持有者
 *
 * @author by yanglikai on 16/08/17.
 */
public class DynamicDataSourceHolder {
    public static final ThreadLocal<String> holder = new InheritableThreadLocal<String>();

    public static void putDataSource(String name) {
        holder.set(name);
    }

    public static String getDataSource() {
        return holder.get();
    }
}
