package bankler.accounts;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Account {

	private String name;
	private String number;
	private Double balance;
	private String currency;

}
