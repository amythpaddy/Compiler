package com.amyth.interpreter.structure.expression;

public class VarLiteral extends Expression {
    String name;
    Expression value;

    public VarLiteral(String name, Expression value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Expression getValue() {
        return value;
    }
}
