package bankler.accounts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

	@Autowired
	private AccountRepository repository;

	/**
	 * Save a new account in the system.
	 */
	public Account addAccount(Account account) throws AccountServiceException {
		if (repository.find(account.getNumber()) == null) {
			return repository.save(account);
		}
		throw new AccountServiceException("An account with the same Number already exist!");
	}

	/**
	 * Get the account info if it exists in the system.
	 */
	public Account getAccountInfo(String number) throws AccountServiceException {
		Account account = repository.find(number);
		if (account != null) {
			return account;
		}
		throw new AccountServiceException("Account doesn't exist!");
	}

}
