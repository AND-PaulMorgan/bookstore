package digital.and.bookstoreapi.book;

import digital.and.bookstoreapi.exceptions.BookNotFoundException;
import digital.and.bookstoreapi.exceptions.IsbnAlreadyUsedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getBooks() { return bookRepository.findAll(); }

    public Optional<Book> getBook(long id) { return bookRepository.findById(id); }

    public Book createBook(Book book) throws IsbnAlreadyUsedException {
        Optional<Book> existingBook = bookRepository.findByIsbn(book.getIsbn());

        if(existingBook.isPresent()) {
            throw new IsbnAlreadyUsedException("That ISBN is already in use");
        }

        return bookRepository.save(book);
    }

    public void deleteBook(long id) throws BookNotFoundException {
        if(!bookRepository.existsById(id)) {
            throw new BookNotFoundException("Book not found");
        }

        bookRepository.deleteById(id);
    }

    public Book updateBook(long id, Book book) throws BookNotFoundException {
        if(!bookRepository.existsById(id)) {
            throw new BookNotFoundException("Book not found");
        }

        return bookRepository.save(book);
    }
}
