package Dictionary;

public class InvalidFrequencyError extends RuntimeException {
    public InvalidFrequencyError() {
    }

    public InvalidFrequencyError(String message) {
        super(message);
    }
}
