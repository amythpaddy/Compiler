package com.amyth.interpreter.structure.statement;

import com.amyth.interpreter.structure.Type.Type;
import com.amyth.interpreter.structure.expression.Expression;
import com.amyth.interpreter.structure.expression.IntLiteral;
import com.amyth.interpreter.structure.expression.StringLiteral;

import java.util.List;

public class FunctionStatement extends Statement {
    String name;
    List<Type> argument;
    Statement body;

    public FunctionStatement(String name) {
        super("function_statement");
        this.name = name;
    }

    public Statement getBody() {
        return body;
    }

    public String getName() {
        return name;
    }

    public void setBody(Statement body) {
        this.body = body;
    }

    public boolean matches(String name){
        return this.name.equals(name);
    }
}
