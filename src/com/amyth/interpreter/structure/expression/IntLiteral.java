package com.amyth.interpreter.structure.expression;

import com.amyth.interpreter.structure.Type.Type;

public class IntLiteral extends Expression {
    int value;


    public IntLiteral(int value) {
        super.setType(Type.INT);
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
