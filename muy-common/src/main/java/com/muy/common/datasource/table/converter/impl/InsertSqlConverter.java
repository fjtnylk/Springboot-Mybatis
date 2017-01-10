package com.muy.common.datasource.table.converter.impl;

import com.muy.common.datasource.table.converter.AbstractSqlConverter;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.insert.Insert;

/**
 * description
 *
 * @author by yanglikai on 16/08/17.
 */
public class InsertSqlConverter extends AbstractSqlConverter{
    protected Statement doConvert(Statement statement, Object params, String mapperId) {
        if (!(statement instanceof Insert)) {
            throw new IllegalArgumentException("The argument statement must is instance of Insert");
        }
        Insert insert = (Insert)statement;
        String name = insert.getTable().getName();
        insert.getTable().setName(this.convertTableName(name, params, mapperId));
        return insert;
    }
}
