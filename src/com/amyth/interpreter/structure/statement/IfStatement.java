package com.amyth.interpreter.structure.statement;

import com.amyth.interpreter.structure.expression.Expression;

public class IfStatement extends Statement {

Statement elseStatement;
Statement thenStatement;
Statement elseIfStatement;
Expression expression;
    public IfStatement(BlockStatement elseIfStatement, Statement elseStatement, Statement thenStatement, Expression expression) {
        super("if_stmt");
        this.expression = expression;
        this.elseStatement = elseStatement;
        this.elseIfStatement = elseIfStatement;
        this.thenStatement = thenStatement;
    }

    public Statement getElseStatement() {
        return elseStatement;
    }

    public Statement getThenStatement() {
        return thenStatement;
    }

    public Expression getExpression() {
        return expression;
    }

    public Statement getElseIfStatement() {
        return elseIfStatement;
    }
}
