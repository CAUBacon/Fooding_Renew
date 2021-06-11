package teaspoon.fooding.api.advice.exception;

public class CRestaurantNotFoundException extends RuntimeException {

    public CRestaurantNotFoundException() {
        super();
    }

    public CRestaurantNotFoundException(String message) {
        super(message);
    }

    public CRestaurantNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

