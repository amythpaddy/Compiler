package com.amyth.interpreter.structure.interpreter.memenv;

import com.amyth.interpreter.structure.Types;

public class InterpreterVariables {
    private final String mIdentifier;
    private final Types.DataType mType;
    private Object mVal;


    public InterpreterVariables(String mIdentifier, Types.DataType mType, Object mVal) {
        this.mIdentifier = mIdentifier;
        this.mType = mType;
        this.mVal = mVal;
    }

    public Object getmVal() {
        return mVal;
    }
    public String getmIdentifier()
    {
        return mIdentifier;
    }

    public Types.DataType getmType()
    {
        return mType;
    }
}
