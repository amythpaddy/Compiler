import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Executor {
    public static void main(String a[]){
        try {
            new Executor().execute();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    CodeGenerator codeGen;
    private void execute() throws FileNotFoundException {
        codeGen = new CodeGenerator();
        Scanner sc = new Scanner(new File("code.txt"));
        while(sc.hasNext()){
            codeGen.analyseCode(sc.nextLine());
        }
    }
}
