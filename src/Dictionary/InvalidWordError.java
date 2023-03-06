package Dictionary;

public class InvalidWordError extends RuntimeException{
    public InvalidWordError() {
    }

    public InvalidWordError(String message) {
        super(message);
    }
}
