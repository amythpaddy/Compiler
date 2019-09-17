package com.amyth.interpreter.structure.statement;

public class PrintStatement extends Statement {
    String message;
    public PrintStatement(String message) {
        super("machine statemenmt");
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
