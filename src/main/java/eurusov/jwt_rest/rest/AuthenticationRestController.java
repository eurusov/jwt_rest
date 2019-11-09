package eurusov.jwt_rest.rest;

import eurusov.jwt_rest.dto.AuthenticationRequestDto;
import eurusov.jwt_rest.model.Authority;
import eurusov.jwt_rest.model.User;
import eurusov.jwt_rest.security.jwt.JwtTokenProvider;
import eurusov.jwt_rest.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping("/api")
public class AuthenticationRestController {

    private AuthenticationManager authenticationManager;

    private JwtTokenProvider jwtTokenProvider;

    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto) {
        try {
            String username = requestDto.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
            User user = userService.getUserWithAuthorities(username);

            String token = jwtTokenProvider.createToken(user);

            Map<String, String> response = new HashMap<>();

            response.put("username", username);
            response.put("token", token);
            response.put("role", getMajorRole(user.getAuthorities()));
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    private String getMajorRole(Set<Authority> authorities) {
        return authorities.stream()
                .min(Comparator.comparing(Authority::getSortOrder))
                .map(Authority::getAuthority)
                .orElse("EMPTY");
    }
}
