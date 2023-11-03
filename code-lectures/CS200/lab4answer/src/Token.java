public class Token {


    public double eval() {
        return -1;
    }

    public static Token parse(String text) {
        if ( text.contains(AddOperator.symbol) ) {
            String[] parts = text.split("\\" + AddOperator.symbol, 2);
            Token lsub = parse(parts[0]);
            Token rsub = parse(parts[1]);
            return new AddOperator(lsub, rsub);
        }
        if ( text.contains(SubOperator.symbol) ) {
            String[] parts = text.split("\\" + SubOperator.symbol, 2);
            Token lsub = parse(parts[0]);
            Token rsub = parse(parts[1]);
            return new SubOperator(lsub, rsub);
        }
        if ( text.contains(MultOperator.symbol) ) {
            String[] parts = text.split("\\" + MultOperator.symbol, 2);
            Token lsub = parse(parts[0]);
            Token rsub = parse(parts[1]);
            return new MultOperator(lsub, rsub);
        }
        if ( text.contains(DivOperator.symbol) ) {
            String[] parts = text.split("\\" + DivOperator.symbol, 2);
            Token lsub = parse(parts[0]);
            Token rsub = parse(parts[1]);
            return new DivOperator(lsub, rsub);
        }else {
            Token num = new Number(Double.parseDouble(text));
            return num;
        }
    }
}
