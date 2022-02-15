package pl.javastart.equipy.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.javastart.equipy.user.dto.UserDto;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    List<UserDto> getUser (@RequestParam(required = false) String lastName) {

        if (lastName != null) {
            return userService.findByLastName(lastName);
        }
        return userService.findAll();
    }

    @GetMapping("/{id}")
    ResponseEntity<UserDto> getUserById(@PathVariable Long id) {

        return userService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    @PutMapping("/{id}")
    ResponseEntity<UserDto> editUser(@PathVariable Long id, @RequestBody UserDto user){

        if (user.getId() != id) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id must be equal to edited object.");
        }

        UserDto userEdited = userService.editUser(id, user);

        return ResponseEntity.ok(userEdited);


    }

    @PostMapping
    ResponseEntity<UserDto> saveUser (@RequestBody UserDto user) {

        if (user.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "New created object id must be null");
        }

        UserDto userSaved = userService.addUser(user);

        URI savedEntityLocation = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userSaved.getId())
                .toUri();

        return ResponseEntity.created(savedEntityLocation).body(userSaved);

    }

}
