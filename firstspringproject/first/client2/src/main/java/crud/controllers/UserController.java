package crud.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import crud.service.UserService;
import crud.service.dto.UserDto;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api", produces = "application/json")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/users")
    public List<UserDto> allUsers() {
        return userService.findAll();
    }

    @GetMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserDto> getUser(@PathVariable Long id){
        return ResponseEntity.ok().body(userService.findById(id));
    }

    @PostMapping(path = "/users", consumes="application/json")
    public ResponseEntity<UserDto> createUser( @RequestBody UserDto user) throws URISyntaxException {
        UserDto result = userService.save(user);
        return ResponseEntity.created(new URI("/api/users/" + result.getId()))
                .body(result);
    }

    @PutMapping(path="/users/{id}", consumes="application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserDto> updateUser( @PathVariable Long id, @RequestBody UserDto user) {
        return ResponseEntity.ok().body(userService.updateById(id, user));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }

	@ExceptionHandler(RuntimeException.class)
    ResponseEntity<Error> userNotFound(RuntimeException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Error(exception.getMessage()));
    }
}
