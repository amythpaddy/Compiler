package com.amyth.interpreter.structure.statement;

public class MotorStatement extends Statement {
    int motorState;
    public MotorStatement(int motorState) {
        super("machine statemenmt");
        this.motorState = motorState;
    }

    public int getMotorState() {
        return motorState;
    }
}
