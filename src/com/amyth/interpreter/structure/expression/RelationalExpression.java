package com.amyth.interpreter.structure.expression;

import com.amyth.interpreter.structure.Types;

public class RelationalExpression extends BinaryExpression {
    Types.DataType op;
    public RelationalExpression(Types.DataType op ,BinaryExpression leftExpr, BinaryExpression rightExpr) {
        super(leftExpr, rightExpr);
        this.op = op;
    }
    public Types.DataType getOperation() {
        return op;
    }

    @Override
    public String toString() {
        return "(" + getLeft() +" "+ op +" "+ getRight() + ")";
    }
}
