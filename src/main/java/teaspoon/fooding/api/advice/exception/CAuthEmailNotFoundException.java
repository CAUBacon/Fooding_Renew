package teaspoon.fooding.api.advice.exception;

public class CAuthEmailNotFoundException extends RuntimeException{
    public CAuthEmailNotFoundException() {
        super();
    }

    public CAuthEmailNotFoundException(String message) {
        super(message);
    }

    public CAuthEmailNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
