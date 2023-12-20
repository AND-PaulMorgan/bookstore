package digital.and.bookstoreapi.validator;

import digital.and.bookstoreapi.exceptions.InvalidIsbnLengthException;

import java.util.Arrays;


public class IsbnValidator {
    public static boolean isValid(String isbn) throws InvalidIsbnLengthException {
        String isbnClean = isbn.replace("-", "");
        int length = isbnClean.length();
        
        if (length == 10) {
            return isValid10(isbnClean);
        }
        
        if (length == 13) {
            return isValid13(isbnClean);
        }
        
        throw new InvalidIsbnLengthException("ISBN must be 10 or 13 digits (excluding hyphens)");
    }

    private static boolean isValid13(String isbnClean) {
        int[] isbn = toIntArray(isbnClean);

        return isbn13VerifyCheckDigit(isbn);
    }

    private static boolean isValid10(String isbnClean) {
        int[] isbn = toIntArray(isbnClean);
        return isbn10ModCheck(isbn) && isbn10VerifyCheckDigit(isbn);
    }

    private static int[] toIntArray(String str) {
        return Arrays.stream(str.split(""))
                .mapToInt((String s) -> "x".equalsIgnoreCase(s) ? 10 : Integer.parseInt(s))
                .toArray();
    }

    private static boolean isbn10ModCheck(int[] isbn) {
        // The sum of the digits, multiplied by their position should be a multiple of 11;
        return 0 == (
            isbn[0] +
            2 * isbn[1] +
            3 * isbn[2] +
            4 * isbn[3] +
            5 * isbn[4] +
            6 * isbn[5] +
            7 * isbn[6] +
            8 * isbn[7] +
            9 * isbn[8] +
            10 * isbn[9]
        ) % 11;
    }

    private static boolean isbn10VerifyCheckDigit(int[] isbn) {
        // The sum of the digits (except the check digit), multiplied by {11 minus their position} should be a multiple of 11;
        return isbn[9] == (11 -
            (
                10 * isbn[0] +
                9 * isbn[1] +
                8 * isbn[2] +
                7 * isbn[3] +
                6 * isbn[4] +
                5 * isbn[5] +
                4 * isbn[6] +
                3 * isbn[7] +
                2 * isbn[8]
            ) % 11
        ) % 11;
    }

    private static boolean isbn13VerifyCheckDigit(int[] isbn) {
        int calculatedCheckDigit = (10 -
            (
                isbn[0] +
                3 * isbn[1] +
                isbn[2] +
                3 * isbn[3] +
                isbn[4] +
                3 * isbn[5] +
                isbn[6] +
                3 * isbn[7] +
                isbn[8] +
                3 * isbn[9] +
                isbn[10] +
                3 * isbn[11]
            ) % 10
        );

        if(calculatedCheckDigit == 10) calculatedCheckDigit = 0;

        return isbn[12] == calculatedCheckDigit;
    }
}
