package digital.and.bookstoreapi.v1;

import digital.and.bookstoreapi.dto.request.v1.CreateUserDto;
import digital.and.bookstoreapi.dto.response.UserResponseDto;
import digital.and.bookstoreapi.exceptions.UsernameUsedException;
import digital.and.bookstoreapi.mapper.v1.UserToUserResponseDtoMapper;
import digital.and.bookstoreapi.user.User;
import digital.and.bookstoreapi.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDto createUser(@RequestBody CreateUserDto userDto) {
        try {
            User user = userService.createUser(userDto);
            return UserToUserResponseDtoMapper.map(user);
        } catch (UsernameUsedException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
