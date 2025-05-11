package banish;

public class BanishException extends RuntimeException{
    public BanishException() {
    }

    public BanishException(String message) {
        super(message);
    }

    public BanishException(String message, Throwable cause) {
        super(message, cause);
    }

    public BanishException(Throwable cause) {
        super(cause);
    }
}
