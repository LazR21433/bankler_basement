package bankler.transactions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

	@Autowired
	private TransactionRepository repository;

	/**
	 * Save a new transaction in the system.
	 */
	public Transaction addTransaction(Transaction transaction) throws TransactionServiceException {
		if (repository.find(transaction) == null) {
			return repository.save(transaction);
		}
		throw new TransactionServiceException("An account with the same Number already exist!");
	}

	/**
	 * Get the transactions for an account if it exists in the system.
	 */
	public List<Transaction> getTransactionsForAccount(String accountNumber) throws TransactionServiceException {
		List<Transaction> transactions = repository.findByAccountNumber(accountNumber);
		if (transactions != null) {
			return transactions;
		}
		throw new TransactionServiceException("Account doesn't exist!");
	}

}
