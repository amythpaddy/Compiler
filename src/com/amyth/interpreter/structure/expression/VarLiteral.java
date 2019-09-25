package com.amyth.interpreter.structure.expression;

import com.amyth.interpreter.structure.Type.Type;

public class VarLiteral extends Expression {
    String name;

    public VarLiteral(String name , Type type) {
        this.name = name;
        setType(type);
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
