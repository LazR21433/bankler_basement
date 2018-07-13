package javamonster.rehab;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

	private Map<String, User> fakeDB = new HashMap<>();

	public User find(String email) {
		return fakeDB.get(email);
	}

	public User save(User user) {
		String email = user.getEmail();
		fakeDB.put(email, user);
		return fakeDB.get(email);
	}

}
