public class Main {

    public static void main(String[] args) {
        Token expr = Token.parse("4 * 5 + 4 / 2 - 1");
        System.out.println(expr + " = " + expr.eval());
    }
}
