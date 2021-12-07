package bank;

import com.banking.bank.Account;
import com.banking.bank.Customer;
import com.banking.bank.exception.CustomerAlreadyExistsException;
import com.banking.bank.exception.CustomerNotOwnerException;
import com.banking.bank.exception.InvalidAmountException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by William Redmond Lawlor
 */
public class LodgementTest extends BaseTest{

    @Test
    public void testLodgeToCorrectAccount() throws InvalidAmountException {
        Customer customer = createCustomer();
        Account accountOne = createAccount(customer);

        accountOne.lodge(50);
        accountOne.lodge(50);
        accountOne.lodge(50);
        accountOne.lodge(50);

        assertEquals(200.00, accountOne.getBalance(), DELTA);
    }

    @Test(expected =  InvalidAmountException.class)
    public void testLodgeInvalidAmount() throws InvalidAmountException {
        Customer customer = createCustomer();
        Account accountOne = createAccount(customer);

        accountOne.lodge(-10);
        assertEquals(0, accountOne.getBalance(), DELTA);
    }
}
