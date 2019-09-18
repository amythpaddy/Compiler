package com.amyth.interpreter.structure.statement;

import com.amyth.interpreter.structure.expression.Expression;

public class VarDeclare extends Statement {
    private String name;
    private Expression var;


    public VarDeclare(String name, Expression var) {
        super("vardeclare");
        this.name = name;
        this.var = var;
    }

    public String getName() {
        return name;
    }

    public Expression getVar() {
        return var;
    }
}
