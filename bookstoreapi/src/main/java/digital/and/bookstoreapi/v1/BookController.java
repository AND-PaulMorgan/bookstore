package digital.and.bookstoreapi.v1;

import digital.and.bookstoreapi.book.Book;
import digital.and.bookstoreapi.book.BookService;
import digital.and.bookstoreapi.dto.request.v1.CreateBookDto;
import digital.and.bookstoreapi.exceptions.BookNotFoundException;
import digital.and.bookstoreapi.exceptions.IsbnAlreadyUsedException;
import digital.and.bookstoreapi.mapper.CreateBookDtoToBookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/book")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getBooks() {
        return bookService.getBooks();
    }

    @GetMapping("{id}")
    public Book getBook(@PathVariable long id) {
        Optional<Book> book = bookService.getBook(id);

        if (book.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book %s not found".formatted(id));
        }

        return book.get();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book createBook(@RequestBody CreateBookDto bookDto) {
        Book createdBook = CreateBookDtoToBookMapper.map(bookDto);

        try {
            return bookService.createBook(createdBook);
        }
        catch(IsbnAlreadyUsedException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable("id") long id) {
        try {
            bookService.deleteBook(id);
        }
        catch(BookNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Book updateBook(@PathVariable("id") long id, @RequestBody Book book) {
        if (book.getId() != id) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id mismatch between url and request body");
        }

        try {
            return bookService.updateBook(id, book);
        }
        catch(BookNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
