package digital.and.bookstoreapi.book;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookServiceTest {
    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    private ArrayList<Book> expected = new ArrayList<>(List.of(
            new Book(1L, "000-1-222-33333-4", "Example Book 1", "Example book for testing", new String[]{"example", "1"}, "Author 1", java.time.LocalDate.of(2011, 1, 1), java.time.LocalDate.of(2023, 6, 1), java.time.LocalDate.of(2023, 6, 1)),
            new Book(2L, "111-2-333-44444-0", "Example Book 2", "Another example book for testing", new String[]{"example", "2"}, "Author 2a and Author 2b", java.time.LocalDate.of(2012, 2, 2), java.time.LocalDate.of(2023, 6, 1), java.time.LocalDate.of(2023, 6, 1)),
            new Book(3L, "222-3-444-55555-6", "Example Book 3", "Yet another example book for testing", new String[]{"example", "3"}, "Author 3", java.time.LocalDate.of(2013, 3, 3), java.time.LocalDate.of(2023, 6, 1), java.time.LocalDate.of(2023, 6, 1))
    ));

    @Test
    void getBooks() {
        Mockito.when(bookRepository.findAll()).thenReturn(expected);

        List<Book> actual = bookService.getBooks();

        assertEquals(expected.size(), actual.size());
        assertEquals(expected.get(0).getId(), actual.get(0).getId());
        assertEquals(expected.get(1).getId(), actual.get(1).getId());
        assertEquals(expected.get(2).getId(), actual.get(2).getId());
    }

    @Test
    void getBook() {
        Mockito.when(bookRepository.findById(1L)).thenReturn(Optional.of(expected.get(0)));

        Optional<Book> actual = bookService.getBook(1L);

        assertTrue(actual.isPresent());
        assertEquals(expected.get(0).getId(), actual.get().getId());
    }

    @Test
    void createBook() {
    }

    @Test
    void deleteBook() {
    }

    @Test
    void updateBook() {
    }
}