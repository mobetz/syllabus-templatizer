public class Number extends Token {

    private double val;

    public Number(double val) {
        this.val = val;
    }

    @Override
    public double eval() {
        return this.val;
    }

    @Override
    public String toString() {
        return Double.toString(this.val);
    }
}
