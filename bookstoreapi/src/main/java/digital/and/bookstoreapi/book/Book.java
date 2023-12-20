package digital.and.bookstoreapi.book;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String isbn;
    private String title;
    private String description;
    private String[] tags;
    private String authors;
    private LocalDate releaseDate;
    private LocalDate creationDate;
    private LocalDate lastModifiedDate;

    public Book(String isbn, String title, String description, String[] tags, String authors, LocalDate releaseDate, LocalDate creationDate, LocalDate lastModifiedDate) {
        this.isbn = isbn;
        this.title = title;
        this.description = description;
        this.tags = tags;
        this.authors = authors;
        this.releaseDate = releaseDate;
        this.creationDate = creationDate;
        this.lastModifiedDate = lastModifiedDate;
    }
}
