package J9;

class CustomFileException extends Exception {
    public CustomFileException(String message) {
        super(message);
    }

    public CustomFileException(String message, Throwable cause) {
        super(message, cause);
    }
}