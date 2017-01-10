package com.muy.common.datasource.table.converter.impl;

import com.muy.common.datasource.table.converter.AbstractSqlConverter;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.delete.Delete;

/**
 * description
 *
 * @author by yanglikai on 16/08/17.
 */
public class DeleteSqlConverter extends AbstractSqlConverter {
    protected Statement doConvert(Statement statement, Object params, String mapperId) {
        if (!(statement instanceof Delete)) {
            throw new IllegalArgumentException("The argument statement must is instance of Delete");
        }
        Delete delete = (Delete) statement;
        String name = delete.getTable().getName();
        delete.getTable().setName(this.convertTableName(name, params, mapperId));
        return delete;
    }
}
