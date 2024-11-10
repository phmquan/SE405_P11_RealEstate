package vn.hoidanit.realestate.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import vn.hoidanit.realestate.domain.User;
import vn.hoidanit.realestate.service.UserService;
import vn.hoidanit.realestate.util.error.IdInvalidException;

@RestController
public class UserController {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }
    @CrossOrigin
    @GetMapping("/")
    public String getHelloWorld() {

        return "Hello world from the other side";
    }

    // @GetMapping("/create")
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User postUser) {
        String hashPassword = passwordEncoder.encode(postUser.getPassword());
        postUser.setPassword(hashPassword);
        this.userService.saveUser(postUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(postUser);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) throws IdInvalidException {
        if (id >= 1500) {
            throw new IdInvalidException("Id khong lon hon 1500");
        }
        if (this.userService.getUserById(id) != null) {
            this.userService.deleteUser(id);

        }
        return ResponseEntity.ok("xoa user " + id + " thanh cong");

    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        User user = this.userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> fetchAll(Model model) {
        return ResponseEntity.ok(this.userService.fetchAllUser());
    }

    @PutMapping("/users")
    public ResponseEntity<User> updateUser(@RequestBody User putUser) {
        return ResponseEntity.ok(this.userService.updateUser(putUser));

    }

}
