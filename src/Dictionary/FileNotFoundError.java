package Dictionary;

public class FileNotFoundError extends RuntimeException {
    public FileNotFoundError() {
    }

    public FileNotFoundError(String message) {
        super(message);
    }
}
