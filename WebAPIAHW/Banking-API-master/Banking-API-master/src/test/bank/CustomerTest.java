package bank;

import com.banking.bank.Account;
import com.banking.bank.Customer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Ailbhe Bennett
 */
public class CustomerTest extends BaseTest{

    @Test
    public void testCreateCustomer() {
        Customer customer = createCustomer();

        assertEquals("Rachel", customer.getFirstname());
        assertEquals("Clayton", customer.getSurname());
        assertEquals("rc@gmail.com", customer.getEmail());
        assertEquals("Limerick", customer.getAddress());
        assertEquals("Rachel.Clayton", customer.getUsername());
    }

    @Test
    public void testEncryptPassword() {
        String cipherText = "8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92";
        Customer customer = createCustomer();

        assertEquals(cipherText, customer.getPassword());
    }

    @Test
    public void testCreateAccount() {
        Customer customer = createCustomer();
        Account account = createAccount(customer);

        assertEquals(account.getOwner(), customer);
    }

    @Test
    public void testCustomerIsOwner() {
        Customer customer = createCustomer();
        Account account = createAccount(customer);

        assertTrue(customer.isOwner(account));
    }

    @Test
    public void testCustomerWithTwoAccounts() {
        Customer customer = createCustomer();
        Account accountOne = createAccount(customer);
        Account accountTwo = createAccount(customer);

        assertTrue(customer.isOwner(accountOne));
        assertNotNull(customer.getAccount(accountTwo.getAccountNumber(), accountTwo.getSortCode()));
    }
}
