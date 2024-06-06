package crud.first.controllers;

import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import crud.first.User;
import crud.first.data.UserRepository;

@RestController
@RequestMapping(path = "/api", produces = "application/json")
public class UserController {

	private UserRepository userRepo;

	public UserController(UserRepository userRepo){
		this.userRepo = userRepo;
	}

	@GetMapping("/users")
	public Iterable<User> personAll() {
		return userRepo.findAll();
	}

	@GetMapping("/users/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Optional<User> personById(@PathVariable("id") Long id) {
		return userRepo.findById(id);
	}

	@PostMapping(path = "/user", consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
    public User postUser(@RequestBody User user) {
		return userRepo.save(user);
    }

	@PutMapping(path="/users/{id}", consumes="application/json")
	public User putUser(
	@PathVariable("id") Long id,
	@RequestBody User user) {
	user.setId(id);
	return userRepo.save(user);
	}

	@PatchMapping(path="/users/{id}", consumes="application/json")
	public User patchUser(@PathVariable("id") Long id,
	@RequestBody User patch) {
		User user = userRepo.findById(id).get();

		if (patch.getFirstName() != null) {
			user.setFirstName(patch.getFirstName());
		}

		if (patch.getLastName() != null) {
			user.setLastName(patch.getLastName());
		}

		if (patch.getEmail() != null) {
			user.setEmail(patch.getEmail());
		}

		if (patch.getPosition() != null) {
			user.setPosition(patch.getPosition());
		}

		return userRepo.save(user);
	}

	@DeleteMapping("/users/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable("id") Long id) {
		try {
			userRepo.deleteById(id);
			} catch (EmptyResultDataAccessException e) {}
	}
}
