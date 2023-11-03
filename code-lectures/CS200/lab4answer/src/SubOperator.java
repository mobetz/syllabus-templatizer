public class SubOperator extends BinaryOperator {

    protected static final String symbol = "-";

    public SubOperator(Token lhs, Token rhs) {
        super(lhs, rhs);
    }

    @Override
    public String getSymbol() {
        return this.symbol;
    }

    @Override
    protected double apply(double l, double r) {
        return l - r;
    }

}
