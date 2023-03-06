package Dictionary;

public class WordNotFoundError extends RuntimeException {
    public WordNotFoundError() {
    }

    public WordNotFoundError(String message) {
        super(message);
    }
}
