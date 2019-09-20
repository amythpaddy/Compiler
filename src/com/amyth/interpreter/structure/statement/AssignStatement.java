package com.amyth.interpreter.structure.statement;

import com.amyth.interpreter.structure.expression.BinaryExpression;
import com.amyth.interpreter.structure.expression.Expression;

public class AssignStatement extends Statement {
    Expression leftExpr;
    Expression rightExpr;
    public AssignStatement(Expression leftExpr, Expression rightExpr) {
        super("assignmentExpr");
        this.leftExpr = leftExpr;
        this.rightExpr = rightExpr;
        leftExpr.setType(rightExpr.getType());
        if(leftExpr.getType() == null)
            throw new RuntimeException("Value cannot be assigned");
    }
}
