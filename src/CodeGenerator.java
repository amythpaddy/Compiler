
import com.amyth.interpreter.structure.Type.Type;
import com.amyth.interpreter.structure.expression.*;
import com.amyth.interpreter.structure.statement.AssignStatement;
import com.amyth.interpreter.structure.statement.BlockStatement;
import com.amyth.interpreter.structure.statement.IfStatement;
import com.amyth.interpreter.structure.statement.VarDeclare;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CodeGenerator {
    static List<VarLiteral> varList = new ArrayList<>();
    BlockStatement statments;
    Scanner sc;
    Pattern startStop = Pattern.compile("(start)(.*)(stop)");
    Pattern if_condition = Pattern.compile("if.*\\((\\d*|[a-z]*)(==|<=|>=|!=|>|<)(\\d*)\\).*");
    Pattern int_declare = Pattern.compile("int\\s([a-z]*)\\s=\\s(\\d*)");
    Pattern string_declare = Pattern.compile("string\\s([a-z]*)\\s=\\s\"([a-z]*)\"");
    Pattern for_loop = Pattern.compile("for\\(([a-z]*)=(\\d*);([a-z]*)(<|<=|>|>=|==)(\\d*);([a-z]*)(.*)\\)");
    Pattern end_block = Pattern.compile(".*}.*");
    Pattern else_if_condition = Pattern.compile("elseif\\((\\d*)(==|<=|>=|!=|>|<)(\\d*)\\).*");
    Pattern else_condition = Pattern.compile("else\\{");
    Pattern assignment = Pattern.compile("([a-z]+)=([a-z]+|\\d+)([-|*|/|+])([a-z]+|\\d+)");

    boolean if_true;
    boolean for_condition;

    public void matchPattern() {
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
        {
//            line = sc.nextLine();
            return;
        }
        else if (assignment.matcher(line).matches())
            assignVariable(assignment.matcher(line));
        else
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
        else
            exp1 = new VarLiteral(arg1);
        if(arg2.matches("\\d*"))
            exp2 = new IntLiteral(Integer.parseInt(arg2));
        else
            exp2 = new VarLiteral(arg2);
        ArithmeticBinExpression arExpr = new ArithmeticBinExpression(exp1,exp2,opr);
        AssignStatement assignStatement = new AssignStatement(new VarLiteral(assignTo),arExpr);
        statments.addBlockStatement(assignStatement);
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
        String value=m.group(2);
        String cond_var_1=m.group(3);
        String condition=m.group(4);
        String cond_var_2=m.group(5);
        String end_var=m.group(6);
        String operation=m.group(7);

        System.out.println("name :"+var);
        System.out.println("int value:"+value);
        System.out.println("arg1:"+cond_var_1);
        System.out.println("operator:"+condition);
        System.out.println("arg2:"+cond_var_2);
        System.out.println("end value:"+end_var);
        System.out.println("operation:"+operation);
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
        varList.add(strExp);
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

        VarLiteral opr1 = new VarLiteral(arg1);
        IntLiteral opr2 = new IntLiteral(Integer.parseInt(arg2));
        RelationalExpression expr = new RelationalExpression(comparator, opr1, opr2);

        BlockStatement thenStatement = new CodeGenerator().analyseCode(sc);
        BlockStatement elseStatement = null;
        if (sc.hasNext()) {
            String line = sc.next();
            if (else_condition.matcher(line).matches())
                elseStatement = new CodeGenerator().analyseCode(sc);
        }
        IfStatement ifStatement = new IfStatement(elseStatement, thenStatement, expr);
        statments.addBlockStatement(ifStatement);
        analyseCode(sc, statments);

    }


}
