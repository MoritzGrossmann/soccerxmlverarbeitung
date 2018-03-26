package database;

public class WrongEntityTypeException extends Exception {

    public WrongEntityTypeException() {
    }

    public WrongEntityTypeException(String message) {
        super(message);
    }

    public WrongEntityTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongEntityTypeException(Throwable cause) {
        super(cause);
    }

    public WrongEntityTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
