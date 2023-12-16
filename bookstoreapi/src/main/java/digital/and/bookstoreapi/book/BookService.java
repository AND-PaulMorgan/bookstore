package digital.and.bookstoreapi.book;

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

    public Book createBook(Book book) {
        Optional<Book> existingBook = bookRepository.findByIsbn(book.getIsbn());

        if(existingBook.isPresent()) {
            throw new IllegalStateException("That ISBN is already in use");
        }

        return bookRepository.save(book);
    }

    public void deleteBook(long id) {
        if(!bookRepository.existsById(id)) {
            throw new IllegalStateException("Book not found");
        }

        bookRepository.deleteById(id);
    }

    public Book updateBook(long id, Book book) {
        if(!bookRepository.existsById(id)) {
            throw new IllegalStateException("Book not found");
        }

        return bookRepository.save(book);
    }
}
