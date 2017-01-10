package com.muy.common.datasource.annotation;

/**
 * 主从库枚举
 *
 * @author by yanglikai on 16/08/17.
 */
public enum DbMsEnum {
    Master("masterDs"),  // 主库
    Slave("slaveDs");    // 从库

    private final String s;

    public String get(){
        return this.s;
    }

    DbMsEnum(String s) {
        this.s = s;
    }
}
