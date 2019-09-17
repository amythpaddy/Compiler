package com.amyth.interpreter.structure.expression;

public abstract class BinaryExpression extends Expression {
    private BinaryExpression leftExpr;
    private BinaryExpression rightExpr;

    public BinaryExpression(BinaryExpression leftExpr, BinaryExpression rightExpr){
        this.leftExpr = leftExpr;
        this.rightExpr = rightExpr;
    }

    public BinaryExpression getLeft() {
        return leftExpr;
    }

    public void setLeft(BinaryExpression leftExpr) {
        this.leftExpr = leftExpr;
    }

    public BinaryExpression getRight() {
        return rightExpr;
    }

    public void setRight(BinaryExpression rightExpr) {
        this.rightExpr = rightExpr;
    }
}
