package bankler.user;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	public UserController(UserService userRepository) {
		this.userService = userRepository;
	}

	@RequestMapping(path = "/user", method = POST)
	public User user(
			@RequestParam(value = "firstname") String firstName,
			@RequestParam(value = "lastname") String lastName,
			@RequestParam(value = "email") String email,
			@RequestParam(value = "password") String password)
			throws UserServiceException {
		return userService.singUp(new User(firstName, lastName, email, password));
	}

	@RequestMapping(path = "/user", method = GET)
	public User getUser(
			@RequestParam(value = "email") String email,
			@RequestParam(value = "password") String password)
			throws UserServiceException {
		return userService.login(email, password);
	}

	@ResponseStatus(value = HttpStatus.FORBIDDEN)
	@ExceptionHandler({ UserServiceException.class })
	public String handleExeption(UserServiceException e) {
		return e.getMessage();
	}
}
