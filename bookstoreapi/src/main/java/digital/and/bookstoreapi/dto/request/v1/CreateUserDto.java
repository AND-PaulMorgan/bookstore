package digital.and.bookstoreapi.dto.request.v1;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public record CreateUserDto(
        @NotBlank
        @Size(min = 1, max = 255, message = "Username cannot be longer than 255 characters")
        String username,
        @NotBlank
        @Size(min = 1, max = 255, message = "Password cannot be longer than 255 characters")
        String password,

        @NotBlank
        List<String> roles
) {}
