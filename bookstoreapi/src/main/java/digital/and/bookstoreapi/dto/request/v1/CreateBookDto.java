package digital.and.bookstoreapi.dto.request.v1;

import java.time.LocalDate;

public record CreateBookDto(String isbn, String title, String description, String[] tags, String authors, LocalDate releaseDate) {

}
