package digital.and.bookstoreapi.mapper.v1;

import digital.and.bookstoreapi.book.Book;
import digital.and.bookstoreapi.v1.dto.CreateBookDto;
import digital.and.bookstoreapi.v1.mapper.CreateBookDtoToBookMapper;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CreateBookDtoToBookMapperTest {

    @Test
    void mapTest() {
        CreateBookDto dto = new CreateBookDto("X", "title", "description", new String[]{"Tag1", "Tag2"}, "authors", LocalDate.of(2022, 2, 2));
        Book book = CreateBookDtoToBookMapper.map(dto);

        assertEquals(dto.isbn(), book.getIsbn());
        assertEquals(dto.title(), book.getTitle());
        assertEquals(dto.description(), book.getDescription());
        assertEquals(dto.tags(), book.getTags());
        assertEquals(dto.authors(), book.getAuthors());
        assertEquals(dto.releaseDate(), book.getReleaseDate());
    }
}