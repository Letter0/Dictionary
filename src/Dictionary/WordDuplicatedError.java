package Dictionary;

public class WordDuplicatedError extends RuntimeException {
    public WordDuplicatedError() {
    }

    public WordDuplicatedError(String message) {
        super(message);
    }
}
