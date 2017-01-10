package com.muy.common.utils;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * Created by yanglikai on 2016/8/17.
 */
public interface MuyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
