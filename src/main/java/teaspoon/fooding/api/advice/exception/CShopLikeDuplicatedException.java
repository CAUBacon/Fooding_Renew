package teaspoon.fooding.api.advice.exception;

public class CShopLikeDuplicatedException extends RuntimeException {
    public CShopLikeDuplicatedException() {
        super();
    }

    public CShopLikeDuplicatedException(String message) {
        super(message);
    }

    public CShopLikeDuplicatedException(String message, Throwable cause) {
        super(message, cause);
    }
}
