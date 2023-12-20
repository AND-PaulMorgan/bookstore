package digital.and.bookstoreapi.mapper;

import digital.and.bookstoreapi.book.Book;
import digital.and.bookstoreapi.dto.request.v1.CreateBookDto;

public class CreateBookDtoToBookMapper {
    public static Book map(CreateBookDto dto) {
        return Book.builder()
                .isbn(dto.isbn())
                .title(dto.title())
                .description(dto.description())
                .tags(dto.tags())
                .authors(dto.authors())
                .releaseDate(dto.releaseDate())
                .build();
    }
}
