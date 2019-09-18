package com.amyth.interpreter.structure.interpreter;

import com.amyth.interpreter.structure.interpreter.memenv.InterpreterVariables;
import com.amyth.interpreter.structure.interpreter.memenv.memEnv;
import com.amyth.interpreter.structure.statement.Statement;

import java.util.ArrayList;

public class TrailInterpreter {
    ArrayList<Statement> mStatementList;
    memEnv memFrame = new memEnv();
    int startIndex, endIndex, presentIndex;

    public TrailInterpreter(ArrayList<Statement> mList)
    {
        mStatementList = mList;
        startIndex =0;
        endIndex = mList.size();
        presentIndex = 0;
    }










}
