public class AddOperator extends Operator {


    public AddOperator(Token lhs, Token rhs) {
        super(lhs, rhs);
    }


    @Override
    public double eval() {
        return this.lhs.eval() + this.rhs.eval();
    }


    @Override
    public String getSymbol() {
        return "+";
    }
    
}
