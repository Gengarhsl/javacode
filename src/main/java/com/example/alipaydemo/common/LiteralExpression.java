package com.example.alipaydemo.common;

import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.ExpressionVisitor;
import net.sf.jsqlparser.parser.SimpleNode;

/**
 * @author hsl
 * 类型转换
 */
public class LiteralExpression implements Expression {
    private final Object value;

    public LiteralExpression(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public void accept(ExpressionVisitor expressionVisitor) {

    }

    @Override
    public SimpleNode getASTNode() {
        return null;
    }

    @Override
    public void setASTNode(SimpleNode simpleNode) {

    }
}