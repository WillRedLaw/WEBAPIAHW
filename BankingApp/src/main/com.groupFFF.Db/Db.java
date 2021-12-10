
import java.util.ArrayList;
import java.util.List;

/**
 *
 *FFF
 */
public class Db {
    public static List<Account> accountDb = new ArrayList<>();
    public static List<Account> accountDb2 = new ArrayList<>();
    public static List<Account> accountDb3 = new ArrayList<>();
    public static List<Customer> customerDb = new ArrayList<>();
    public static List<Transaction> transactionDb = new ArrayList<>();
    public static List<Movie> transacionDb2 = new ArrayList<>();
    public static List<Movie> transactionDb3 = new ArrayList<>();
    
    public static List<Account> getAccountsDB() {
        return accountDB;
    }
    
    public static List<Customer> getCustomersDB() {
        return customerDB;
    }
    
    public static List<Movie> getMoviesDB() {
        return movieDB;
    }
    
}
