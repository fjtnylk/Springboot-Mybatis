package com.muy.common.datasource.table.converter;

import net.sf.jsqlparser.statement.Statement;

/**
 * sql转换修改接口
 *
 * @author by yanglikai on 16/08/17.
 */
public interface SqlConverter {
    String convert(Statement statement, Object params, String mapperId);
}
