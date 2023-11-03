public class DivOperator extends BinaryOperator {


    protected static final String symbol = "/";

    DivOperator(Token lhs, Token rhs) {
        super(lhs, rhs);
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    protected double apply(double l, double r) {
        return l / r;
    }
}
