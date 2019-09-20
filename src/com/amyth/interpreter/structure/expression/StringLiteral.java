package com.amyth.interpreter.structure.expression;

import com.amyth.interpreter.structure.Type.Type;

public class StringLiteral extends Expression {
    String value;

    public StringLiteral(String value) {
        super.setType(Type.STRING);
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
