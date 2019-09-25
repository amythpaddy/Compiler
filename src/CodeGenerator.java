
import com.amyth.interpreter.structure.Type.Type;
import com.amyth.interpreter.structure.expression.*;
import com.amyth.interpreter.structure.statement.*;

import javax.swing.plaf.nimbus.State;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CodeGenerator {
    static List<VarDeclare> varList = new ArrayList<>();

    BlockStatement statments;
    List<FunctionStatement> functionStatement;
    BlockStatement onAPressed;
    BlockStatement onBPressed;
    BlockStatement onABPressed;


    Scanner sc;
    Pattern startStop = Pattern.compile("(start)(.*)(stop)");
    Pattern if_condition = Pattern.compile("if.*\\((\\d+|[a-z]+)(==|<=|>=|!=|>|<)(\\d+|[a-z]+)\\).*");
    Pattern int_declare = Pattern.compile("int\\s([a-z]*)\\s=\\s(\\d*)");
    Pattern string_declare = Pattern.compile("string\\s([a-z]*)\\s=\\s\"([a-z]*)\"");
    Pattern for_loop = Pattern.compile("for\\(([a-z]*)=(\\d*);([a-z]*)(<|<=|>|>=|==)(\\d*);([a-z]*)(\\+\\+|--)\\)\\{");
    Pattern end_block = Pattern.compile(".*}.*");
    Pattern else_if_condition = Pattern.compile("elseif\\((\\d*)(==|<=|>=|!=|>|<)(\\d*)\\).*");
    Pattern else_condition = Pattern.compile("else\\{");
    Pattern elseif_statement = Pattern.compile("elseif\\{");
    Pattern assignment = Pattern.compile("([a-z]+)=([a-z]+|\\d+)([-|*|/|+])([a-z]+|\\d+)");
    Pattern repeatStatement = Pattern.compile("repeat\\((\\d+)\\)\\{");
    Pattern function_declaration = Pattern.compile("func ([a-z]+//d*).*");
    Pattern function_call = Pattern.compile("([a-z]+\\d*)\\(\\).*");
    Pattern A_press_pattern = Pattern.compile("([a-z]+\\d*)\\(\\).*");

    boolean if_true;
    boolean for_condition;

    public void  matchPattern() {
        String line = sc.nextLine();
        if (int_declare.matcher(line).matches())
            getIntLiteral(int_declare.matcher(line));
        else if (string_declare.matcher(line).matches())
            getStringLiteral(string_declare.matcher(line));
        else if (if_condition.matcher(line).matches())
            getIfStatement(if_condition.matcher(line));
        else if (for_loop.matcher(line).matches())
            getForStatement(for_loop.matcher(line));
        else if (end_block.matcher(line).matches())
            return;
        else if (assignment.matcher(line).matches())
            assignVariable(assignment.matcher(line));
        else if (repeatStatement.matcher(line).matches())
            setRepeatStatement(repeatStatement.matcher(line));
        else if (function_declaration.matcher(line).matches())
            setFunctionDeclaration(function_declaration.matcher(line));
        else if(function_call.matcher(line).matches())
            setFunctionCall(function_call.matcher(line));
        else
            analyseCode(sc,statments);
    }

    private void setFunctionCall(Matcher matcher) {
        matcher.reset();
        matcher.find();
        String name = matcher.group(1);
        FunctionCallStatement functionCallStatement = null;
        try {
            for (FunctionStatement func : functionStatement) {
                if (func.matches(name)) {
                    functionCallStatement = new FunctionCallStatement(name);
                    break;
                }
            }
            if (functionCallStatement != null)
                statments.addBlockStatement(functionCallStatement);
            else
                throw new RuntimeException(name + " is not a predefined function");
        }catch (NullPointerException e){
            throw new RuntimeException(name + " is not a predefined function");
        }
    }

    private void setFunctionDeclaration(Matcher matcher) {
        if(functionStatement == null)
            functionStatement = new ArrayList<>();
        matcher.reset();
        matcher.find();
        String name = matcher.group(1);

        FunctionStatement function = new FunctionStatement(name);
        function.setBody(new CodeGenerator().analyseCode(sc));
        functionStatement.add(function);
        analyseCode(sc,statments);
    }

    private void setRepeatStatement(Matcher m) {
        m.reset();
        m.find();
        int count=Integer.parseInt(m.group(1));

        System.out.println("count :"+count);
        RepeatStatement countStatement = new RepeatStatement(count);
        Statement executeStmt = new CodeGenerator().analyseCode(sc);
        countStatement.setExecute(executeStmt);
        statments.addBlockStatement(countStatement);
        analyseCode(sc,statments);
    }

    private void assignVariable(Matcher matcher) {
        matcher.reset();
        matcher.find();

        String assignTo = matcher.group(1);
        String arg1 = matcher.group(2);
        String opr = matcher.group(3);
        String arg2 = matcher.group(4);
        Expression exp1 = null;
        Expression exp2 = null;
        if(arg1.matches("\\d*"))
            exp1 = new IntLiteral(Integer.parseInt(arg1));
        else {
            for (VarDeclare var : varList) {
                exp1 = var.match(arg1);
                if (exp1 != null)
                    break;
            }
            if (exp1 == null) {
                throw new RuntimeException(arg1 + " is not declared.");
            }
        }
        if(arg2.matches("\\d*"))
            exp2 = new IntLiteral(Integer.parseInt(arg2));
        else {
            for (VarDeclare var : varList) {
                exp2 = var.match(arg2);
                if (exp2 != null)
                    break;
            }
            if (exp2 == null) {
                throw new RuntimeException(arg2 + " is not declared.");
            }
        }
        ArithmeticBinExpression arExpr = new ArithmeticBinExpression(exp1,exp2,opr);
        VarDeclare assignStatement = new VarDeclare(assignTo, arExpr);
        statments.addBlockStatement(assignStatement);
        varList.add(assignStatement);
        analyseCode(sc, statments)  ;
    }

    public BlockStatement analyseCode(Scanner sc) {
        this.sc = sc;
        statments = new BlockStatement();
        if (sc.hasNext()) {
            matchPattern();
        }
        return statments;
    }

    public void analyseCode(Scanner sc, BlockStatement statments) {
        this.sc = sc;
        this.statments = statments;
        if (sc.hasNext()) {
            matchPattern();
        }

    }

    private void getForStatement(Matcher m) {
        m.reset();
        m.find();
        String var=m.group(1);
        int value=Integer.parseInt(m.group(2));
        String cond_var_1=m.group(3);
        String condition=m.group(4);
        int cond_var_2=Integer.parseInt(m.group(5));
        String end_var=m.group(6);
        String operation=m.group(7);

        System.out.println("name :"+var);
        System.out.println("int value:"+value);
        System.out.println("arg1:"+cond_var_1);
        System.out.println("operator:"+condition);
        System.out.println("arg2:"+cond_var_2);
        System.out.println("end value:"+end_var);
        System.out.println("operation:"+operation);
        VarDeclare declare = new VarDeclare(var,new IntLiteral(value));
        varList.add(declare);
        RelationalExpression conditionExp = new RelationalExpression(condition,declare.match(cond_var_1),new IntLiteral(cond_var_2));
        UnaryExpression performOperation = new UnaryExpression(declare.match(end_var)
                ,operation.equals("++")? UnaryExpression.UnaryOperator.INCR: UnaryExpression.UnaryOperator.DECR);
        ForStatement forStatement = new ForStatement(declare, conditionExp, performOperation);
        Statement executeStmt = new CodeGenerator().analyseCode(sc);
        forStatement.setExecute(executeStmt);
        statments.addBlockStatement(forStatement);
        analyseCode(sc,statments);
    }

    private void getIntLiteral(Matcher m) {
        m.reset();
        m.find();
        String name=m.group(1);
        String value=m.group(2);

        System.out.println("name :"+name);
        System.out.println("int value:"+value);

        IntLiteral intexp = new IntLiteral(Integer.parseInt(value));
        VarDeclare stmt = new VarDeclare(name, intexp);
        stmt.setType(Type.INT);
        varList.add(stmt);
        statments.addBlockStatement(stmt);

        analyseCode(sc, statments);
    }

    private void getStringLiteral(Matcher m) {
        m.reset();
        m.find();
        String name=m.group(1);
        String value=m.group(2);

        System.out.println("name :"+name);
        System.out.println("string value:"+value);

        StringLiteral strExp = new StringLiteral(value);
        VarDeclare stmt = new VarDeclare(name, strExp);
        stmt.setType(Type.STRING);
        varList.add(stmt);
        statments.addBlockStatement(stmt);

        analyseCode(sc, statments);
    }

    private void getIfStatement(Matcher group) {
        group.reset();
        group.find();
        String arg1=group.group(1);
        String comparator=group.group(2);
        String arg2=group.group(3);

        System.out.println("arg1 :"+arg1);
        System.out.println("comp :"+comparator);
        System.out.println("arg2 :"+arg2);

        Expression opr1 = null;

        if(arg1.matches("\\d*"))
            opr1 = new IntLiteral(Integer.parseInt(arg1));
        else {
            for (VarDeclare var : varList) {
                opr1 = var.match(arg1);
                if (opr1 != null)
                    break;
            }
            if (opr1 == null) {
                throw new RuntimeException(arg1 + " is not declared.");
            }
        }

        Expression opr2 = null;

        if(arg1.matches("\\d*"))
            opr2 = new IntLiteral(Integer.parseInt(arg2));
        else {
            for (VarDeclare var : varList) {
                opr2 = var.match(arg2);
                if (opr2 != null)
                    break;
            }
            if (opr2 == null) {
                throw new RuntimeException(arg2 + " is not declared.");
            }
        }

        RelationalExpression expr = new RelationalExpression(comparator, opr1, opr2);

        BlockStatement thenStatement = new CodeGenerator().analyseCode(sc);
        BlockStatement elseStatement = null;
        BlockStatement elseIfStatement = null;

        if (sc.hasNext()) {
            String line = sc.next();
            if (elseif_statement.matcher(line).matches())
                elseIfStatement = new CodeGenerator().analyseCode(sc);
        }
        if (sc.hasNext()) {
            String line = sc.next();
            if (else_condition.matcher(line).matches())
                elseStatement = new CodeGenerator().analyseCode(sc);
        }
        IfStatement ifStatement = new IfStatement(elseIfStatement,elseStatement, thenStatement, expr);
        statments.addBlockStatement(ifStatement);
        analyseCode(sc, statments);

    }



}
