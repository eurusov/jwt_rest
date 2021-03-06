package eurusov.jwt_rest.rest;

import eurusov.jwt_rest.model.Authority;
import eurusov.jwt_rest.model.User;
import eurusov.jwt_rest.service.AuthorityService;
import eurusov.jwt_rest.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/admin")
@AllArgsConstructor
public class AdminRestController {
    private UserService userService;
    private AuthorityService authorityService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/list")
    public List<User> getUserList() {
        return userService.getUserList();
    }

    @GetMapping("/user")
    public User getPrincipal(@RequestParam(required = false) Long id, @RequestParam(required = false) String name) {
        if (id != null) {
            return userService.getOneWithAuthorities(id);
        }
        if (name != null) {
            return userService.getUserWithAuthorities(name);
        }
        return null;
    }

    @GetMapping("/authorities")
    public List<Authority> getAuthorities() {
        return authorityService.getAll();
    }

    @PostMapping("/add")
    public List<User> addNewUser(@RequestBody User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userService.addUser(user);
        return userService.getUserList();
    }

    @PutMapping("/update")
    public List<User> updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return userService.getUserList();
    }

    @DeleteMapping("delete/{userId}")
    public List<User> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return userService.getUserList();
    }
}
