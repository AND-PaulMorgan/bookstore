package digital.and.bookstoreapi.validator;

import digital.and.bookstoreapi.v1.dto.CreateBookDto;
import digital.and.bookstoreapi.exceptions.InvalidIsbnLengthException;

public class CreateBookDtoValidator {
    public static boolean isValid(CreateBookDto dto) throws InvalidIsbnLengthException {
        return IsbnValidator.isValid(dto.isbn());
    }
}
