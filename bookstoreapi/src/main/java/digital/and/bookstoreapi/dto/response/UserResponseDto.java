package digital.and.bookstoreapi.dto.response;

import java.util.List;

public record UserResponseDto(
    Long id,
    String username,
    List<String>roles,
    boolean enabled,
    boolean expired,
    boolean locked) {

}
