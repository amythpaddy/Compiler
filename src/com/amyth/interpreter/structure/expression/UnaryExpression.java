package com.amyth.interpreter.structure.expression;

public class UnaryExpression extends Expression {
    private Expression operand;

    public enum UnaryOperator {
        INCR("++"), DECR("--"), NOT("!");

        private String repr;

        private UnaryOperator(String repr) {
            this.repr = repr;
        }

        @Override
        public String toString() {
            return repr;
        }
    }

    UnaryOperator operator;

    public UnaryExpression(Expression operand, UnaryOperator operator) {
        this.operand = operand;
        this.operator = operator;
    }

    public Expression getOperand() {
        return operand;
    }

    public UnaryOperator getOperator() {
        return operator;
    }
}
