package com.amyth.interpreter.structure.expression;

import com.amyth.interpreter.structure.Types;

public class RelationalExpression extends BinaryExpression {
    String op;
    public RelationalExpression(String op ,BinaryExpression leftExpr, BinaryExpression rightExpr) {
        super(leftExpr, rightExpr);
        this.op = op;
    }
    public String getOperation() {
        return op;
    }

    @Override
    public String toString() {
        return "(" + getLeft() +" "+ op +" "+ getRight() + ")";
    }
}
