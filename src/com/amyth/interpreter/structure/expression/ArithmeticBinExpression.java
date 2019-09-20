package com.amyth.interpreter.structure.expression;

import com.amyth.interpreter.structure.Type.Type;

public class ArithmeticBinExpression extends BinaryExpression {
    private enum ArithmeticBinOperation{
        PLUS("+"), MINUS("-"), MULT("*"), DIV("/"), MOD("%");

        private String repr;

        ArithmeticBinOperation(String repr) {
            this.repr = repr;
        }

        @Override
        public String toString() { return repr; }
    }

    ArithmeticBinOperation operation;
    public ArithmeticBinExpression(Expression leftExpr, Expression rightExpr, String operation) {
        super(leftExpr, rightExpr);
        switch (operation){
            case "/":this.operation =  ArithmeticBinOperation.DIV;
                super.setType(leftExpr.getType().div(rightExpr.getType()));
                break;
            case "+":this.operation =  ArithmeticBinOperation.PLUS;
            super.setType(leftExpr.getType().plus(rightExpr.getType()));
                break;
            case "-":this.operation =  ArithmeticBinOperation.MINUS;
                super.setType(leftExpr.getType().minus(rightExpr.getType()));
                break;
            case "*":this.operation =  ArithmeticBinOperation.MULT;
                super.setType(leftExpr.getType().mul(rightExpr.getType()));
                break;
            case "%":this.operation =  ArithmeticBinOperation.MOD;
                super.setType(leftExpr.getType().modulus(rightExpr.getType()));
                break;

        }

        if(getType() == null)
            throw new RuntimeException("Operation not compatible");

    }

    public ArithmeticBinOperation getOperation() {
        return operation;
    }

    @Override
    public void setType(Type type) {
        super.setType(type);
    }
}
