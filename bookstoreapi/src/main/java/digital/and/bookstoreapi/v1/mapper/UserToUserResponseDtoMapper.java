package digital.and.bookstoreapi.v1.mapper;

import digital.and.bookstoreapi.v1.dto.UserResponseDto;
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
