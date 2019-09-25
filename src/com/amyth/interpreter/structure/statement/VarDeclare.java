package com.amyth.interpreter.structure.statement;

import com.amyth.interpreter.structure.Type.Type;
import com.amyth.interpreter.structure.expression.Expression;
import com.amyth.interpreter.structure.expression.VarLiteral;

public class VarDeclare extends Statement {
    private VarLiteral nameLiteral;
    private Expression valLiteral;


    public VarDeclare(String nameLiteral, Expression valLiteral) {
        super("vardeclare");
        this.nameLiteral = new VarLiteral(nameLiteral, valLiteral.getType());
        this.valLiteral = valLiteral;
    }

    public Expression getNameLiteral() {
        return nameLiteral;
    }

    public Expression getValLiteral() {
        return valLiteral;
    }

    public void setType(Type type){
        valLiteral.setType(type);
    }

    public VarLiteral match(String arg1) {
        if (nameLiteral.getName().equals(arg1))
            return nameLiteral;
        else
            return null;
    }
}
