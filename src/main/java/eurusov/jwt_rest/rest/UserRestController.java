package eurusov.jwt_rest.rest;

import eurusov.jwt_rest.model.User;
import eurusov.jwt_rest.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserRestController {

    private UserService userService;

    @GetMapping
    public User getPrincipal(Principal principal) {
        return userService.getUserWithAuthorities(principal.getName());
    }
}
