public class SubOperator extends Operator {


    public SubOperator(Token left, Token right) {
        super(left, right);
    }


    @Override
    public double eval() {
        return this.lhs.eval() - this.rhs.eval();
    }

    
    @Override
    public String getSymbol() {
        return "-";
    }
    
    
}
