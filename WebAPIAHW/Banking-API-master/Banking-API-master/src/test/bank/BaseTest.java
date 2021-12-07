package bank;

import com.banking.bank.Account;
import com.banking.bank.Customer;

/**
 * Created by Hannah ORourke
 */
public class BaseTest {

    protected static final double DELTA = 1e-15;

    protected Customer createCustomer() {
        return new Customer("Rachel", "Clayton", "rc@gmail.com", "Limerick", "Rachel.Clayton", "123456");
    }

    protected Account createAccount(Customer customer) {
        return new Account(customer);
    }
}
