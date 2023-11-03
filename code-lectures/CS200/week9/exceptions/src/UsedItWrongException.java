public class UsedItWrongException extends RuntimeException {

    private String hint;

    public UsedItWrongException(String hint) {
        this.hint = hint;
    }
    @Override
    public String getMessage() {
        return this.hint;
    }
}
