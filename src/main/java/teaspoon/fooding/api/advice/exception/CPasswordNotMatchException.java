package teaspoon.fooding.api.advice.exception;

public class CPasswordNotMatchException extends RuntimeException{
    public CPasswordNotMatchException() {
        super();
    }

    public CPasswordNotMatchException(String message) {
        super(message);
    }

    public CPasswordNotMatchException(String message, Throwable cause) {
        super(message, cause);
    }
}
