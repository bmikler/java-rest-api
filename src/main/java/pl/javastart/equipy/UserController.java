package pl.javastart.equipy;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    List<User> getUser (@RequestParam(required = false) String lastName) {

        if (lastName != null) {
            return userRepository.findByLastNameContainsIgnoreCase(lastName);
        }
        return userRepository.findAll();
    }

    @PostMapping
    public void saveUser (@RequestBody User user) {

        User userSaved = userRepository.save(user);

    }

}
