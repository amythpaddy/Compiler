package com.amyth.interpreter.structure.statement;

public class FunctionCallStatement extends Statement {
    String name;

    public FunctionCallStatement(String name) {
        super("function_call_statement");
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean matches(String name) {
        return this.name.equals(name);
    }
}
