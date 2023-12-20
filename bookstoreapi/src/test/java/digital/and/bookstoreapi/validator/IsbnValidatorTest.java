package digital.and.bookstoreapi.validator;

import digital.and.bookstoreapi.exceptions.InvalidIsbnLengthException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IsbnValidatorTest {

    @Test
    void isValidReturnsTrueWithGoodIsbn10() {
        String isbn = "1-234-56789-X";
        try {
            assertTrue(IsbnValidator.isValid(isbn));
        } catch (InvalidIsbnLengthException e) {
            fail();
        }
    }

    @Test
    void isValidReturnsFalseWithBadIsbn10() {
        String isbn = "1-234-56789-0";
        try {
            assertFalse(IsbnValidator.isValid(isbn));
        } catch (InvalidIsbnLengthException e) {
            fail();
        }
    }


    @Test
    void isValidReturnsTrueWithGoodIsbn13() {
        String isbn = "123-4-56-789012-8";
        try {
            assertTrue(IsbnValidator.isValid(isbn));
        } catch (InvalidIsbnLengthException e) {
            fail();
        }
    }

    @Test
    void isValidReturnsFalseWithBadIsbn13() {
        String isbn = "123-4-56-789012-0";
        try {
            assertFalse(IsbnValidator.isValid(isbn));
        } catch (InvalidIsbnLengthException e) {
            fail();
        }
    }

    @Test
    void isValidThrowsExceptionWithIrregularLengthIsbnString() {
        String isbn = "123456789";
        Exception e = assertThrows(InvalidIsbnLengthException.class, () -> IsbnValidator.isValid(isbn));
        assertEquals("ISBN must be 10 or 13 digits (excluding hyphens)", e.getMessage());
    }
}