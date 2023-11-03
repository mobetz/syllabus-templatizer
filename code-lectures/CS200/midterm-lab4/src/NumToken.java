public class NumToken extends Token {

    private double value;


    public NumToken(double val) {
        this.value = val;
    }


    @Override
    public double eval() {
        return this.value;
    }


    public static Token parse(String input) {
        double input_as_num = Double.parseDouble(input);
        return new NumToken(input_as_num);

    }
}
