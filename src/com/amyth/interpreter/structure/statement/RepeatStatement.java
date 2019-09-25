package com.amyth.interpreter.structure.statement;

import com.amyth.interpreter.structure.expression.Expression;
import com.amyth.interpreter.structure.expression.IntLiteral;

public class RepeatStatement extends Statement {
    Expression count;
    Statement execute;

    public RepeatStatement(int count) {
        super("repeat_statement");
        this.count = new IntLiteral(count);
    }

    public Expression getCount() {
        return count;
    }

    public Statement getExecute() {
        return execute;
    }

    public void setExecute(Statement execute) {
        this.execute = execute;
    }
}
