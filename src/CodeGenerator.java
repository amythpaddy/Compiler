
import com.amyth.interpreter.structure.expression.IntLiteral;
import com.amyth.interpreter.structure.expression.RelationalExpression;
import com.amyth.interpreter.structure.expression.StringLiteral;
import com.amyth.interpreter.structure.expression.VarLiteral;
import com.amyth.interpreter.structure.statement.BlockStatement;
import com.amyth.interpreter.structure.statement.VarDeclare;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CodeGenerator {
    BlockStatement statments;
    Scanner sc;
    Pattern startStop = Pattern.compile("(start)(.*)(stop)");
    Pattern if_condition = Pattern.compile("if.*\\((\\d*)(==|<=|>=|!=|>|<)(\\d*)\\).*\\{(.*)\\}");
    Pattern int_declare = Pattern.compile("int\\s([a-z]*)\\s=\\s(\\d*)");
    Pattern string_declare = Pattern.compile("string\\s([a-z]*)\\s=\\s\"([a-z]*)\"");
    Pattern for_loop = Pattern.compile("for\\(([a-z]*)=(\\d*);([a-z]*)(<|<=|>|>=|==)(\\d*);([a-z]*)(.*)\\)");

    boolean if_true;
    boolean for_condition;



    public void analyseCode(Scanner sc) {
        this.sc = sc;
        statments = new BlockStatement();
        if (sc.hasNext()) {
            String line = sc.nextLine();
            if (int_declare.matcher(line).matches())
                getIntLiteral(int_declare.matcher(line));
            else if (string_declare.matcher(line).matches())
                getStringLiteral(string_declare.matcher(line));
            else if (if_condition.matcher(line).matches())
                getIfStatement(if_condition.matcher(line));
            else if (for_loop.matcher(line).matches())
                getForStatement(for_loop.matcher(line));
        }

    }

    public void analyseCode(Scanner sc, BlockStatement statments) {
        this.sc = sc;
        this.statments = statments;
        if (sc.hasNext()) {
            String line = sc.nextLine();
            if (int_declare.matcher(line).matches())
                getIntLiteral(int_declare.matcher(line));
            else if (string_declare.matcher(line).matches())
                getStringLiteral(string_declare.matcher(line));
            else if (if_condition.matcher(line).matches())
                getIfStatement(if_condition.matcher(line));
            else if (for_loop.matcher(line).matches())
                getForStatement(for_loop.matcher(line));
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
        statments.addBlockStatement(stmt);

        analyseCode(sc, statments);
    }

    private void getIfStatement(Matcher group) {
        group.reset();
        group.find();
        String arg1=group.group(1);
        String comparator=group.group(2);
        String arg2=group.group(3);
        String execute = group.group(4);

        System.out.println("arg1 :"+arg1);
        System.out.println("comp :"+comparator);
        System.out.println("arg2 :"+arg2);
        System.out.println("execute :"+execute);

        IntLiteral opr1 = new VarLiteral();
        IntLiteral opr2 = new IntLiteral(Integer.parseInt(arg2));
        RelationalExpression expr = new RelationalExpression(comparator,)
    }


}
