package com.amyth.interpreter.structure.expression;

import com.amyth.interpreter.structure.Type.Type;

public abstract class Expression {
    private Type type;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
