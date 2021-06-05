package teaspoon.fooding.api.advice.exception;

public class CNicknameDuplicatedException extends RuntimeException{
    public CNicknameDuplicatedException() {
        super();
    }

    public CNicknameDuplicatedException(String message) {
        super(message);
    }

    public CNicknameDuplicatedException(String message, Throwable cause) {
        super(message, cause);
    }
}
