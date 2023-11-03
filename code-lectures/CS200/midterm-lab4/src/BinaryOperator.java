public abstract class BinaryOperator extends Token {

    protected Token lhs;
    protected Token rhs;


    public BinaryOperator(Token lhs, Token rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public double eval() {
        return apply(this.lhs.eval(), this.rhs.eval());
    }

    public abstract double apply(double left, double right);
}
