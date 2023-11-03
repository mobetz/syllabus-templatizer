public class DivToken extends BinaryOperator {


    public DivToken(Token left, Token right) {
        super(left, right);
    }

    @Override
    public double apply(double left, double right) {
        return left / right;
    }


    public static Token parse(String input) {
        DivToken ret;
        if ( input.contains("/") ) {
            String[] parts = input.split("/", 2);
            //Both of my "sides" of my expression are also Tokens:
            String left = parts[0];
            String right = parts[1];

            Token left_tok = Token.parse(left);
            Token right_tok = Token.parse(right);

            ret = new DivToken(left_tok, right_tok);
        }
        else {
            ret = null;

        }

        return ret;
    }
}
