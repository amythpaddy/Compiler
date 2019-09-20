package com.amyth.interpreter.structure.statement;

import com.amyth.interpreter.structure.Type.Type;
import com.amyth.interpreter.structure.expression.Expression;
import com.amyth.interpreter.structure.expression.StringLiteral;
import com.amyth.interpreter.structure.expression.VarLiteral;

public class VarDeclare extends Statement {
    private Expression name;
    private Expression var;


    public VarDeclare(String name, Expression var) {
        super("vardeclare");
        this.name = new VarLiteral(name);
        this.var = var;
        if(var instanceof StringLiteral)
            var.setType(Type.STRING);
        else
            var.setType(Type.INT);
    }

    public String getName() {
        return name;
    }

    public Expression getVar() {
        return var;
    }

    public void setType(Type type){
        var.setType(type);
    }
}
