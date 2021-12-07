import com.banking.bank.Account;
import com.banking.bank.Customer;
import com.banking.bank.exception.CustomerAlreadyExistsException;
import com.banking.bank.exception.CustomerNotOwnerException;
import com.banking.bank.exception.InsufficientFundsException;
import com.banking.bank.exception.InvalidAmountException;
import com.banking.controller.InteractionController;


/**
 * Created by Hannah ORourke
 */
public class JPA {
    public static void main(String args[]) throws CustomerAlreadyExistsException, CustomerNotOwnerException, InvalidAmountException, InsufficientFundsException {
        InteractionController controller = new InteractionController();

        Customer customer = controller.getCustomerById(1);
        Account account = customer.getAccount(7935974, 3964);

        controller.lodge(account, 500);

        //controller.teardown();
    }
}
