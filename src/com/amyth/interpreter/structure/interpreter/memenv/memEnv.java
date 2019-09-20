package com.amyth.interpreter.structure.interpreter.memenv;

import com.amyth.interpreter.structure.Types;

import java.util.ArrayList;

public class memEnv {
    private ArrayList<InterpreterVariables> mVaraibleList;

    public memEnv()
    {
        mVaraibleList = new ArrayList<>();
    }


    public boolean addNewVariable(String id, Types.DataType type, Object val)
    {
        boolean alreadyExists = false;
        for(InterpreterVariables v : mVaraibleList )
        {
            if(v.getmIdentifier().equals(id))
                alreadyExists = true;
        }
        if(alreadyExists)
            return false;
        else
        {
            mVaraibleList.add(new InterpreterVariables(id, type, val));
            return true;
        }
    }

    public Object mGetVariableValue(String id)
    {

        InterpreterVariables mVar = null;
        for(InterpreterVariables v : mVaraibleList )
        {
            if(v.getmIdentifier().equals(id))
            {
                mVar  = v;
                break;
            }
        }
        if(mVar!=null)
           return  mVar.getmVal();
        return null;
    }

}
