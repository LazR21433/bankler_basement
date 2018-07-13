package javamonster.rehab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	/**
	 * Save a new user in the system.
	 */
	public User singUp(User user) throws UserServiceException {
		if (repository.find(user.getEmail()) == null) {
			return repository.save(user);
		}
		throw new UserServiceException("A user with the same E-Mail adress already exist!");
	}

	/**
	 * Check if the user exists in the system and authenticate him/her.
	 */
	public User login(String email, String password) throws UserServiceException {
		User user = repository.find(email);
		if (user != null && user.checkPassword(password)) {
			return user;
		}
		throw new UserServiceException("Authentication failed!");
	}

}
