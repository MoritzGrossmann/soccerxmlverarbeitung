package database;

public class Result {

    private boolean success;

    private String message;

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public Result(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    @Override
    public String toString() {
        return String.format("Success: %b\tMessage: %s", this.success, this.message);
    }
}
