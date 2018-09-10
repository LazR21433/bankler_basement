package bankler.transactions;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

	@Autowired
	private TransactionService service;

	public TransactionController(TransactionService service) {
		this.service = service;
	}

	@RequestMapping(path = "/transaction", method = POST)
	public Transaction addTransaction(
			@RequestParam(value = "accountnumber") String accountNumber,
			@RequestParam(value = "name") String name,
			@RequestParam(value = "number") String number,
			@RequestParam(value = "amount") Double amount,
			@RequestParam(value = "date") Date date,
			@RequestParam(value = "category") String category)
			throws TransactionServiceException {
		return service.addTransaction(new Transaction(accountNumber, name, number, amount, date, category));
	}

	@RequestMapping(path = "/transactions", method = GET)
	public List<Transaction> getTransactionsForAccount(@RequestParam(value = "accountnumber") String accountnumber)
			throws TransactionServiceException {
		return service.getTransactionsForAccount(accountnumber);
	}

	@ResponseStatus(value = HttpStatus.FORBIDDEN)
	@ExceptionHandler({ TransactionServiceException.class })
	public String handleExeption(TransactionServiceException e) {
		return e.getMessage();
	}
}
