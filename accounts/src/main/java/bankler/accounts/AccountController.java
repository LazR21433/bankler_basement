package bankler.accounts;

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
public class AccountController {

	@Autowired
	private AccountService service;

	public AccountController(AccountService service) {
		this.service = service;
	}

	@RequestMapping(path = "/account", method = POST)
	public Account post(
			@RequestParam(value = "name") String name,
			@RequestParam(value = "number") String number,
			@RequestParam(value = "balance") Double balance,
			@RequestParam(value = "currency") String currency)
			throws AccountServiceException {
		return service.addAccount(new Account(name, number, balance, currency));
	}

	@RequestMapping(path = "/account", method = GET)
	public Account post(@RequestParam(value = "number") String number)
			throws AccountServiceException {
		return service.getAccountInfo(number);
	}

	@ResponseStatus(value = HttpStatus.FORBIDDEN)
	@ExceptionHandler({ AccountServiceException.class })
	public String handleExeption(AccountServiceException e) {
		return e.getMessage();
	}
}
