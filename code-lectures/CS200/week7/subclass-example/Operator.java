public abstract class Operator extends Token {

    //ATTRIBUTES
    protected Token lhs;
    protected Token rhs;

    //METHODS
    public Operator(Token lhs, Token rhs) {
        super();
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public abstract String getSymbol();

    @Override
    public String toString() {
        return this.lhs.toString() + " " + this.getSymbol() + " " + this.rhs.toString(); 
    }
    
}
