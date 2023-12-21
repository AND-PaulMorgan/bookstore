package digital.and.bookstoreapi.mapper.v1;

import digital.and.bookstoreapi.dto.response.UserResponseDto;
import digital.and.bookstoreapi.user.User;

public class UserToUserResponseDtoMapper {
    public static UserResponseDto map(User user) {
        return new UserResponseDto(
                user.getId(),
                user.getUsername(),
                user.getRoles(),
                user.isEnabled(),
                user.isExpired(),
                user.isLocked()
        );
    }
}
