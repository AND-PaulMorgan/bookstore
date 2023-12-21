package digital.and.bookstoreapi.v1.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestDto(
        @NotBlank
        String username,

        @NotBlank
        String password) {
}
