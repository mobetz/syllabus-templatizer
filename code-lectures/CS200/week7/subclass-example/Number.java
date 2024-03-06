public class Number extends Token {

    //ATTRIBUTES
    private double value;



    //METHODS
    public Number(double val) {
        super();
        this.value = val;
    };

    @Override
    public double eval() {
        return this.value;
    }


    public String toString() {
        return String.valueOf(this.value);
    }
    
}
