package com.amyth.interpreter.structure.expression;

public class StringLiteral extends Expression {
    String value;

    public StringLiteral(String value) {
        super.setType("integer");
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
