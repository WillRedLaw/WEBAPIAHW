package com.banking.bank;

import com.banking.bank.exception.CustomerAlreadyExistsException;
import com.banking.deserialzer.CustomerDeserializer;
import com.banking.util.HashUtil;
import org.codehaus.jackson.map.annotate.JsonDeserialize;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Hannah OR
 * @version 1.0
 */

@Entity
@Table
@Inheritance
@XmlRootElement
@JsonDeserialize(using = CustomerDeserializer.class)
public class Customer extends Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected int id;
    @OneToMany(cascade = CascadeType.ALL, mappedBy="owner")
    protected List<Account> accounts;
    protected String username;
    protected String password;

    public Customer() {

    }

    /**
     *
     * @param firstname
     * @param surname
     * @param email
     * @param address
     * @param username
     * @param password
     * @throws CustomerAlreadyExistsException
     */
    public Customer(String firstname,
                    String surname,
                    String email,
                    String address,
                    String username,
                    String password) {
        super(firstname, surname, email, address);

        id = 0;
        accounts = new ArrayList<>();
        setUsername(username);
        setPassword(password);
    }

    public void setPassword(String password) {
        this.password = HashUtil.sha256(password);
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    /**
     *
     * @param account
     * @return
     */
    public boolean isOwner(Account account) {
        for (Account tempAccount : accounts) {
            if (tempAccount.getOwner().equals(account.getOwner())) {
                return true;
            }
        }

        return false;
    }

    /**
     *
     * @param accountNumber
     * @param sortCode
     * @return Account
     * @see Account
     */
    public Account getAccount(long accountNumber, int sortCode) {
        for (Account tempAccount : accounts) {
            if (tempAccount.getAccountNumber() == accountNumber && tempAccount.getSortCode() == sortCode) {
                return tempAccount;
            }
        }

        return null;
    }

    public boolean isValid() {
        return !firstname.isEmpty() && !surname.isEmpty() && !username.isEmpty()
                && !password.isEmpty() && !email.isEmpty() && !address.isEmpty();
    }
}
