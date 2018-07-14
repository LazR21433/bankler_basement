package bankler.accounts;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class AccountRepository {

	private Map<String, Account> fakeDB = new HashMap<>();

	public Account find(String number) {
		return fakeDB.get(number);
	}

	public Account save(Account account) {
		String number = account.getNumber();
		fakeDB.put(number, account);
		return fakeDB.get(number);
	}

}
