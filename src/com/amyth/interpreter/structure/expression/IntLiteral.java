package com.amyth.interpreter.structure.expression;

public class IntLiteral extends Expression {
    int value;


    public IntLiteral(int value) {
        super.setType("integer");
        this.value = value;

    }

    public int getValue() {
        return value;
    }

}
