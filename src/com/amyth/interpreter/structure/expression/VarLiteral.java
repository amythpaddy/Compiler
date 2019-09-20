package com.amyth.interpreter.structure.expression;

public class VarLiteral extends Expression {
    String name;
    Expression value;

    public VarLiteral(String name, Expression value) {
        this.name = name;
        this.value = value;
        setType(value.getType());
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
