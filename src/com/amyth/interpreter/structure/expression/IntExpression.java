package com.amyth.interpreter.structure.expression;

public class IntExpression extends Expression {
    int value;

    public IntExpression(int value) {
        super.setType("integer");
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
