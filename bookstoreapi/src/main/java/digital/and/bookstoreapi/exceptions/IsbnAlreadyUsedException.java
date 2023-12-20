package digital.and.bookstoreapi.exceptions;

public class IsbnAlreadyUsedException extends Exception {
    public IsbnAlreadyUsedException() {
    }

    public IsbnAlreadyUsedException(String message) {
        super(message);
    }
}
