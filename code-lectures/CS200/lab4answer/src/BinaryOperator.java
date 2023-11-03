public class BinaryOperator extends Token {
    protected Token lhs;
    protected Token rhs;


    public BinaryOperator(Token lhs, Token rhs) {
        super();
        this.lhs = lhs;
        this.rhs = rhs;
    }

    protected double apply(double l, double r) {
        return l;
    }

    protected String getSymbol() {
        return AddOperator.symbol;
    }


    @Override
    public final double eval() {
        double result = apply(lhs.eval(), rhs.eval());

        return result;
    }

    @Override
    public String toString() {
        return  this.lhs + " " + getSymbol() + " " + this.rhs;
    }


}
