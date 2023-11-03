public class AddToken extends BinaryOperator {

    public static String symbol = "+";
    public static String regex = "\\+";


    public AddToken(Token given_left, Token given_right) {
        super(given_left, given_right);
    }

    @Override
    public double apply(double left, double right) {
        return left + right;
    }



    public static Token parse(String input) {
        AddToken ret;
        if ( input.contains("+") ) {
            String[] parts = input.split("\\+", 2);
            //Both of my "sides" of my expression are also Tokens:
            String left = parts[0];
            String right = parts[1];

            Token left_tok = Token.parse(left);
            Token right_tok = Token.parse(right);

            ret = new AddToken(left_tok, right_tok);
        }
        else {
            ret = null;

        }

        return ret;
    }
}
