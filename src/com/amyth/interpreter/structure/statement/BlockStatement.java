package com.amyth.interpreter.structure.statement;

import java.util.ArrayList;
import java.util.List;

public class BlockStatement extends Statement{

    private List<Statement> blockStatement;
    public BlockStatement() {
        super("block");
        blockStatement = new ArrayList<>();
    }

    public void addBlockStatement(Statement stmt){
        blockStatement.add(stmt);
    }

    public List<Statement> getBlockStatement(){
        return blockStatement;
    }

}
