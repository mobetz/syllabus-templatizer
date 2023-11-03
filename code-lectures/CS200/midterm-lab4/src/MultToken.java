public class MultToken extends BinaryOperator{

    public MultToken(Token left, Token right) {
        super(left, right);
    }

    @Override
    public double apply(double left, double right) {
        return left * right;
    }


    public static Token parse(String input) {
        MultToken ret;
        if ( input.contains("*") ) {
            String[] parts = input.split("\\*", 2);
            //Both of my "sides" of my expression are also Tokens:
            Token left_tok = Token.parse(parts[0]);
            Token right_tok = Token.parse(parts[1]);
            ret = new MultToken(left_tok, right_tok);
        }
        else {
            ret = null;

        }

        return ret;
    }
}
