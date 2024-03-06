import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        

        System.out.print("Enter an expression: ");
        Scanner in = new Scanner(System.in);

        

        Token expression = Token.parse(in.nextLine());

        double result = expression.eval();
        System.out.println("The result of the expression " + expression + " = " + result);
    }
    
}
