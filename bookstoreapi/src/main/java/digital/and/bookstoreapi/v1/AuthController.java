package digital.and.bookstoreapi.v1;

import digital.and.bookstoreapi.util.JwtUtil;
import digital.and.bookstoreapi.v1.dto.LoginRequestDto;
import digital.and.bookstoreapi.user.UserService;
import digital.and.bookstoreapi.v1.dto.LoginResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    public AuthController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto loginRequestDto) {
        Authentication auth;

        try {
            auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.username(), loginRequestDto.password()));
        }
        catch(AuthenticationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

        if (auth.isAuthenticated()) {
            return new LoginResponseDto(loginRequestDto.username(), JwtUtil.createToken(loginRequestDto.username()));
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot authenticate with those credentials");
    }
}
