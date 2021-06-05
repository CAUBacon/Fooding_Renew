package teaspoon.fooding.api.advice.exception;

public class CEmailDuplicatedException extends RuntimeException{
    public CEmailDuplicatedException() {
        super();
    }

    public CEmailDuplicatedException(String message) {
        super(message);
    }

    public CEmailDuplicatedException(String message, Throwable cause) {
        super(message, cause);
    }
}
