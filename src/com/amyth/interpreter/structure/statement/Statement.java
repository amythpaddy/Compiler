package com.amyth.interpreter.structure.statement;

import com.amyth.interpreter.structure.Type.Type;

public abstract class Statement {
    String type="";
    public Statement(String type){
        this.type = type;
    }
    @Override
    public String toString() {
        return "A "+type+" statement";
    }


}
