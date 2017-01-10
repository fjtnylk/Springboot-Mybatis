package com.muy.common.datasource.table.converter.impl;

import com.muy.common.datasource.table.converter.AbstractSqlConverter;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.update.Update;

/**
 * description
 *
 * @author by yanglikai on 16/08/17.
 */
public class UpdateSqlConverter extends AbstractSqlConverter {
    protected Statement doConvert(Statement statement, Object params, String mapperId) {
        if (!(statement instanceof Update)) {
            throw new IllegalArgumentException("The argument statement must is instance of Update.");
        }
        Update update = (Update) statement;
        String name = update.getTables().get(0).getName();
        update.getTables().get(0).setName(this.convertTableName(name, params, mapperId));
        return update;
    }
}
