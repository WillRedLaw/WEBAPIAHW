package bank;

import com.banking.bank.Account;
import com.banking.bank.Customer;
import com.banking.bank.exception.CustomerNotOwnerException;
import com.banking.bank.exception.InsufficientFundsException;
import com.banking.bank.exception.InvalidAmountException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by William Redmond Lawlor
 */
public class WithdrawalTest extends BaseTest{

    @Test
    public void testWithdraw() throws InvalidAmountException, InsufficientFundsException {
        Customer customer = createCustomer();
        Account account = createAccount(customer);

        account.lodge(500);
        account.withdraw(50);
        assertEquals(450, account.getBalance(), DELTA);
    }

    @Test(expected = InsufficientFundsException.class)
    public void testWithdrawWithZeroBalance() throws InvalidAmountException, InsufficientFundsException {
        Customer customer = createCustomer();
        Account account = createAccount(customer);

        account.withdraw(50);
        assertEquals(0, account.getBalance(), DELTA);
    }
}

