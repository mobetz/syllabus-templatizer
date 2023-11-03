public abstract class Token {


    public abstract double eval();


    public static Token parse(String expr_as_text) {



        Token token = SubToken.parse(expr_as_text);
        if ( token == null ) {
            token = AddToken.parse(expr_as_text);
            if (token == null) {
                token = MultToken.parse(expr_as_text);
                if ( token == null ) {
                    token = DivToken.parse(expr_as_text);
                    if ( token == null) {
                        token = NumToken.parse(expr_as_text);
                    }
                }
            }
        }
        return token;
    }
}
