package one.ua.exceptions;

public class DataException extends RuntimeException {

    public DataException() {
    }

    public DataException(String message, Throwable exception) {
        super(message, exception);
    }

    public DataException(String message) {
        super(message);
    }

    public DataException(Throwable exception) {
        super(exception);
    }
}
