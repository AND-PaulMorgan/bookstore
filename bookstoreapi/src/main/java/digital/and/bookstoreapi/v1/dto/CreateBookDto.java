package digital.and.bookstoreapi.v1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record CreateBookDto(
        @NotBlank(message = "ISBN cannot be blank")
        @Size(min = 10, max = 18, message ="ISBN must be between 10 and 18 characters")
        String isbn,

        @NotBlank(message = "Title cannot be blank")
        @Size(min=1, max=255, message = "Title must be between 1 and 255 characters")
        String title,

        @Size(min=0, max=255, message = "Description is optional but must not be longer than 255 characters")
        String description,
        String[] tags,

        @NotBlank(message = "You must list at least one author")
        @Size(min=1, max=255, message = "Author(s) are limited to 255 characters")
        String authors,
        LocalDate releaseDate) {

}
