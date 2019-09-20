package com.amyth.interpreter.structure.expression;

public abstract class BinaryExpression extends Expression {
    private Expression leftExpr;
    private Expression rightExpr;

    public BinaryExpression(Expression leftExpr, Expression rightExpr){
        this.leftExpr = leftExpr;
        this.rightExpr = rightExpr;
    }

    public Expression getLeft() {
        return leftExpr;
    }

    public void setLeft(BinaryExpression leftExpr) {
        this.leftExpr = leftExpr;
    }

    public Expression getRight() {
        return rightExpr;
    }

    public void setRight(BinaryExpression rightExpr) {
        this.rightExpr = rightExpr;
    }
}
