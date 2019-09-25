package com.amyth.interpreter.structure.statement;

import com.amyth.interpreter.structure.expression.Expression;

public class ForStatement extends Statement {
    Statement initilization;
    Expression conditional;
    Expression increament;
    Statement execute;

    public ForStatement(Statement initilization, Expression conditional, Expression increament) {
        super("for_statement");
        this.initilization = initilization;
        this.conditional = conditional;
        this.increament = increament;
    }

    public Statement getInitilization() {
        return initilization;
    }

    public Expression getConditional() {
        return conditional;
    }

    public Expression getIncreament() {
        return increament;
    }

    public Statement getExecute() {
        return execute;
    }

    public void setExecute(Statement execute) {
        this.execute = execute;
    }
}
