package com.muy.common.datasource.table.converter.impl;

import com.muy.common.datasource.table.converter.AbstractSqlConverter;
import net.sf.jsqlparser.expression.*;
import net.sf.jsqlparser.expression.operators.arithmetic.*;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
import net.sf.jsqlparser.expression.operators.relational.*;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.*;

import java.util.Iterator;

/**
 * description
 *
 * @author by yanglikai on 16/08/17.
 */
public class SelectSqlConverter extends AbstractSqlConverter{
    protected Statement doConvert(Statement statement, final Object params, final String mapperId) {
        if (!(statement instanceof Select)) {
            throw new IllegalArgumentException("The argument statement must is instance of Select.");
        }
        TableNameModifier modifier = new TableNameModifier(params, mapperId);
        ((Select)statement).getSelectBody().accept(modifier);
        return statement;
    }

    private class TableNameModifier implements SelectVisitor, FromItemVisitor, ExpressionVisitor, ItemsListVisitor {
        private Object params;
        private String mapperId;

        TableNameModifier(Object params, String mapperId) {
            this.params = params;
            this.mapperId = mapperId;
        }

        @Override
        public void visit(NullValue nullValue) {

        }

        @Override
        public void visit(Function function) {

        }

        @Override
        public void visit(SignedExpression signedExpression) {

        }

        @Override
        public void visit(JdbcParameter jdbcParameter) {

        }

        @Override
        public void visit(JdbcNamedParameter jdbcNamedParameter) {

        }

        @Override
        public void visit(DoubleValue doubleValue) {

        }

        @Override
        public void visit(LongValue longValue) {

        }

        @Override
        public void visit(HexValue hexValue) {

        }

        @Override
        public void visit(DateValue dateValue) {

        }

        @Override
        public void visit(TimeValue timeValue) {

        }

        @Override
        public void visit(TimestampValue timestampValue) {

        }

        @Override
        public void visit(Parenthesis parenthesis) {

        }

        @Override
        public void visit(StringValue stringValue) {

        }

        @Override
        public void visit(Addition addition) {
            visitBinaryExpression(addition);
        }

        @Override
        public void visit(Division division) {
            visitBinaryExpression(division);
        }

        @Override
        public void visit(Multiplication multiplication) {

        }

        @Override
        public void visit(Subtraction subtraction) {

        }

        @Override
        public void visit(AndExpression andExpression) {
            visitBinaryExpression(andExpression);
        }

        @Override
        public void visit(OrExpression orExpression) {

        }

        @Override
        public void visit(Between between) {
            between.getLeftExpression().accept(this);
            between.getBetweenExpressionStart().accept(this);
            between.getBetweenExpressionEnd().accept(this);
        }

        @Override
        public void visit(EqualsTo equalsTo) {
            visitBinaryExpression(equalsTo);
        }

        @Override
        public void visit(GreaterThan greaterThan) {
            visitBinaryExpression(greaterThan);
        }

        @Override
        public void visit(GreaterThanEquals greaterThanEquals) {
            visitBinaryExpression(greaterThanEquals);
        }

        @Override
        public void visit(InExpression inExpression) {
            inExpression.getLeftExpression().accept(this);
            inExpression.getRightItemsList().accept(this);
        }

        @Override
        public void visit(IsNullExpression isNullExpression) {

        }

        @Override
        public void visit(LikeExpression likeExpression) {
            visitBinaryExpression(likeExpression);
        }

        @Override
        public void visit(MinorThan minorThan) {

        }

        @Override
        public void visit(MinorThanEquals minorThanEquals) {

        }

        @Override
        public void visit(NotEqualsTo notEqualsTo) {

        }

        @Override
        public void visit(Column column) {

        }

        @Override
        public void visit(CaseExpression caseExpression) {

        }

        @Override
        public void visit(WhenClause whenClause) {

        }

        @Override
        public void visit(ExistsExpression existsExpression) {
            existsExpression.getRightExpression().accept(this);
        }

        @Override
        public void visit(AllComparisonExpression allComparisonExpression) {

        }

        @Override
        public void visit(AnyComparisonExpression anyComparisonExpression) {

        }

        @Override
        public void visit(Concat concat) {

        }

        @Override
        public void visit(Matches matches) {

        }

        @Override
        public void visit(BitwiseAnd bitwiseAnd) {

        }

        @Override
        public void visit(BitwiseOr bitwiseOr) {

        }

        @Override
        public void visit(BitwiseXor bitwiseXor) {

        }

        @Override
        public void visit(CastExpression castExpression) {

        }

        @Override
        public void visit(Modulo modulo) {

        }

        @Override
        public void visit(AnalyticExpression analyticExpression) {

        }

        @Override
        public void visit(WithinGroupExpression withinGroupExpression) {

        }

        @Override
        public void visit(ExtractExpression extractExpression) {

        }

        @Override
        public void visit(IntervalExpression intervalExpression) {

        }

        @Override
        public void visit(OracleHierarchicalExpression oracleHierarchicalExpression) {

        }

        @Override
        public void visit(RegExpMatchOperator regExpMatchOperator) {

        }

        @Override
        public void visit(JsonExpression jsonExpression) {

        }

        @Override
        public void visit(RegExpMySQLOperator regExpMySQLOperator) {

        }

        @Override
        public void visit(UserVariable userVariable) {

        }

        @Override
        public void visit(NumericBind numericBind) {

        }

        @Override
        public void visit(KeepExpression keepExpression) {

        }

        @Override
        public void visit(MySQLGroupConcat mySQLGroupConcat) {

        }

        @Override
        public void visit(RowConstructor rowConstructor) {

        }

        @Override
        public void visit(OracleHint oracleHint) {

        }

        @Override
        public void visit(Table tableName) {
            String table = tableName.getName();
            table = convertTableName(table, params, mapperId);
            // convert table name
            tableName.setName(table);
        }

        @Override
        public void visit(SubSelect subSelect) {
            subSelect.getSelectBody().accept(this);
        }

        @Override
        public void visit(ExpressionList expressionList) {

        }

        @Override
        public void visit(MultiExpressionList multiExpressionList) {

        }

        @Override
        public void visit(SubJoin subJoin) {

        }

        @Override
        public void visit(LateralSubSelect lateralSubSelect) {

        }

        @Override
        public void visit(ValuesList valuesList) {

        }

        @Override
        public void visit(TableFunction tableFunction) {

        }

        @Override
        public void visit(PlainSelect plainSelect) {
            plainSelect.getFromItem().accept(this);

            if (plainSelect.getJoins() != null) {
                for (Iterator jonsIt = plainSelect.getJoins().iterator(); jonsIt.hasNext();) {
                    Join join = (Join) jonsIt.next();
                    join.getRightItem().accept(this);
                }
            }

            if (plainSelect.getWhere() != null) {
                plainSelect.getWhere().accept(this);
            }
        }



        @Override
        public void visit(SetOperationList setOperationList) {

        }

        @Override
        public void visit(WithItem withItem) {

        }

        public void visitBinaryExpression(BinaryExpression binaryExpression) {
            binaryExpression.getLeftExpression().accept(this);
            binaryExpression.getRightExpression().accept(this);
        }
    }
}
