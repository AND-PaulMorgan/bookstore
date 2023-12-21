package digital.and.bookstoreapi.user;

import digital.and.bookstoreapi.dto.request.v1.CreateUserDto;
import digital.and.bookstoreapi.exceptions.UsernameUsedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User '%s' not found".formatted(username)));
    }

    public User createUser(CreateUserDto userDto) throws UsernameUsedException {
        Optional<User> existingUser = userRepository.findByUsername(userDto.username());
        if (existingUser.isPresent()) {
            throw new UsernameUsedException("There is already a user named %s".formatted(userDto.username()));
        }

        User newUser = new User(
                userDto.username(),
                passwordEncoder.encode(userDto.password()),
                userDto.roles().stream().map(role -> role.toUpperCase()).toList()
        );

        return userRepository.save(newUser);
    }
}
