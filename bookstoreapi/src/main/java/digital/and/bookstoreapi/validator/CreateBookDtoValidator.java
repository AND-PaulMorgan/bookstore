package digital.and.bookstoreapi.validator;

import digital.and.bookstoreapi.dto.request.v1.CreateBookDto;
import digital.and.bookstoreapi.exceptions.InvalidIsbnLengthException;

public class CreateBookDtoValidator {
    public static boolean isValid(CreateBookDto dto) throws InvalidIsbnLengthException {
        return IsbnValidator.isValid(dto.isbn());
    }
}
