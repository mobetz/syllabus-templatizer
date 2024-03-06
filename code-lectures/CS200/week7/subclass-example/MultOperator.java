public class MultOperator extends Operator {


    public MultOperator(Token left, Token right) {
        super(left, right);
    }


    @Override
    public double eval() {
        return this.lhs.eval() * this.rhs.eval();
    }

    
    @Override
    public String getSymbol() {
        return "*";
    }
    
    
}
