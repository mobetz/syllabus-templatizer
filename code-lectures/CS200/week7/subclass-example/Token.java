

public abstract class Token {


    public abstract double eval();
    

    public static Token parse(String expression) {

        expression = expression.trim();


        if ( expression.contains("+")) {
            String[] parts = expression.split("\\+", 2);
            Token left_side = Token.parse(parts[0]);
            Token right_side = Token.parse(parts[1]);
            return new AddOperator(left_side, right_side);
        } else if ( expression.contains("-")) {
            String[] parts = expression.split("-", 2);
            Token left_side = Token.parse(parts[0]);
            Token right_side = Token.parse(parts[1]);
            return new SubOperator(left_side, right_side);
        }else if ( expression.contains("*")) {
            String[] parts = expression.split("\\*", 2);
            Token left_side = Token.parse(parts[0]);
            Token right_side = Token.parse(parts[1]);
            return new MultOperator(left_side, right_side);
        } else if ( expression.contains("/")) {
            String[] parts = expression.split("/", 2);
            Token left_side = Token.parse(parts[0]);
            Token right_side = Token.parse(parts[1]);
            return new DivOperator(left_side, right_side);
        } 




        double value = Double.parseDouble(expression);
        return new Number(value);



    }
    
}