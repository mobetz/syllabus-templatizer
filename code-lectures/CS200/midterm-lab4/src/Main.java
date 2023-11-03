import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


        Scanner input = new Scanner(System.in);
        System.out.print("Enter an expression: " );
        String expr = input.nextLine();

        Token some_expr = Token.parse(expr);
        System.out.println(expr + "="+some_expr.eval());
    }
}
