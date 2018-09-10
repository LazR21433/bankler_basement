package bankler.transactions;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Transaction {

	private String accountNumber;
	private String name;
	private String number;
	private Double amount;
	private Date date;
	private String category;

}
