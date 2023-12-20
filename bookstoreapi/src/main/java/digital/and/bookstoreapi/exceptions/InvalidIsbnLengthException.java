package digital.and.bookstoreapi.exceptions;

public class InvalidIsbnLengthException extends Exception {
    public InvalidIsbnLengthException() {
    }

    public InvalidIsbnLengthException(String message) {
        super(message);
    }
}
