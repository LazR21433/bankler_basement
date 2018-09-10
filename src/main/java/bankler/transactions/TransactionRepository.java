package bankler.transactions;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

@Repository
public class TransactionRepository {

	private List<Transaction> fakeDB = new ArrayList<Transaction>();

	public Transaction find(Transaction transaction) {
		return fakeDB.get(fakeDB.indexOf(transaction));
	}

	public List<Transaction> findByAccountNumber(String accountNumber) {
		return fakeDB.stream().filter(t -> t.getAccountNumber().equals(accountNumber)).collect(Collectors.toList());
	}

	public Transaction save(Transaction transaction) {
		fakeDB.add(transaction);
		return fakeDB.get(fakeDB.indexOf(transaction));
	}

}
