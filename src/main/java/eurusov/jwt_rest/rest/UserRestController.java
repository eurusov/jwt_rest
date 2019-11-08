package eurusov.jwt_rest.rest;

import eurusov.jwt_rest.model.User;
import eurusov.jwt_rest.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin
@RequestMapping("/api")
@AllArgsConstructor
public class UserRestController {

    private UserService userService;

    @GetMapping("/user")
    public User getPrincipal(Principal principal) {
        return userService.getUserWithAuthorities(principal.getName());
    }

    @GetMapping("/user/{username}")
    public User getUserByName(@PathVariable String username) {
        return userService.getUserWithAuthorities(username);
    }
}
