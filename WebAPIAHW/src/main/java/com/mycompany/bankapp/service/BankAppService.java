/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bankapp.service;

import com.mycompany.bankapp.model.Account;
import com.mycompany.bankapp.model.Customer;
import com.mycompany.bankapp.model.Transaction;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 *
 * @author
 * 
 * 
 */
public class BankAppService {
    
    public static List<Customer> list = new ArrayList<>();
    
    //------------------------------------------------------------------------------------------------------------------
    //----------------------------------------ADMIN METHODS-------------------------------------------------------------
    // 1.1 and 1.2. TO CREATE MOCK CUSTOMERS
    
    public List<Customer> createCustomer(){
        Customer c1 = new Customer(1, "Petra Furkes", "Street 5, Dublin 1, Ireland", "petra@mail.com", "password111");
        Customer c2 = new Customer(2, "James Kelly", "Street 10, Dublin 2, Ireland", "james@mail.com", "password222");
        Customer c3 = new Customer(3, "Maria Lundstrom", "Street 15, Dublin 3, Ireland", "maria@mail.com", "password333");
        
        //build up the actual Customer list
        list.add(c1);
        list.add(c2);
        list.add(c3);
       
        //----------ACCOUNT----FIRST CUSTOMER---Petra Furkes------------------------------------------------------------
        //Account data for the first user - Petra Furkes
        List<Account> a1List = new ArrayList<>();
        //accountID - 10001 for Visa Debit, 10002 for Visa Credit, 10003 for Master Card, 10004 for Saving Account
        Account c11 = new Account("DUB01", 10001, "Visa Debit", 15487542, 5000);
        Account c12 = new Account("DUB01", 10002, "Visa Credit", 24587426, 7000);
        Account c13 = new Account("DUB01", 10003, "Master Card", 54782451, 7000);
        Account c14 = new Account("DUB01", 10004, "Saving Account", 24571240, 6000);
        
        a1List.add(c11);
        a1List.add(c12);
        a1List.add(c13);
        a1List.add(c14);
        //transactions for the Visa Debit card
        List<Transaction> trVisaDebitList = new ArrayList<>();
        Transaction t111 = new Transaction(001, "Visa Debit", "Withdrawal",6000, 400);
        Transaction t112 = new Transaction(002, "Visa Debit", "Withdrawal",5500, 500);
        Transaction t113 = new Transaction(003, "Visa Debit", "Withdrawal",5200, 300);
        Transaction t114 = new Transaction(004, "Visa Debit", "Withdrawal",5000, 200);
        
        trVisaDebitList.add(t111);
        trVisaDebitList.add(t112);
        trVisaDebitList.add(t113);
        trVisaDebitList.add(t114);
        
        //to set data into VisaDebit customer's transaction list
        c11.setTransactionList(trVisaDebitList);

        //transactions for the Visa Credit card
        List<Transaction> trVisaCreditList = new ArrayList<>();
        Transaction t211 = new Transaction(001, "Visa Credit", "Lodgement", 6000, 400);
        Transaction t212 = new Transaction(002, "Visa Credit", "Lodgement", 6500, 500);
        Transaction t213 = new Transaction(003, "Visa Credit", "Lodgement", 6800, 300);
        Transaction t214 = new Transaction(004, "Visa Credit", "Lodgement", 7000, 200);
        
        trVisaCreditList.add(t211);
        trVisaCreditList.add(t212);
        trVisaCreditList.add(t213);
        trVisaCreditList.add(t214);
        
        //to set data into VisaCredit customer's transaction list
        c12.setTransactionList(trVisaCreditList);
        
        //to set all data into first customer's card list
        c1.setAccountList(a1List);
      
        
        //----------ACCOUNT----SECOND CUSTOMER---James Kelly------------------------------------------------------------
        //Account data for second user - James Kelly
        List<Account> a2List = new ArrayList<>();
        //accountID - 10001 for VisaDebit, 10002 for VisaCredit, 10003 for MasterCard
        //10004 for SaverAccount
        Account c21 = new Account("DUB01", 10001, "Visa Debit", 65472154, 5000);
        Account c22 = new Account("DUB01", 10002, "Visa Credit", 85471254, 7000);
        Account c23 = new Account("DUB01", 10003, "Master Card", 31275201, 7000);
        Account c24 = new Account("DUB01", 10004, "Saving Account", 03175214, 6000);
        
        a2List.add(c21);
        a2List.add(c22);
        a2List.add(c23);
        a2List.add(c24);
        
        //to set data into second customer's card list
        c2.setAccountList(a2List);
      
        
        //----------ACCOUNT----THIRD CUSTOMER---Maria Lundstrom---------------------------------------------------------
        //Account data for third user - Maria Lundstrom
        List<Account> a3List = new ArrayList<>();
        //accountID - 10001 for Visa Debit, 10002 for Visa Credit, 10003 for MasterCard
        //10004 for Saving Account
        Account c31 = new Account("DUB01", 10001, "Visa Debit", 21438541, 3000);
        Account c32 = new Account("DUB01", 10002, "Visa Credit", 65204752, 5000);
        Account c33 = new Account("DUB01", 10004, "Saving Account", 99945217, 4000);
        
        a3List.add(c31);
        a3List.add(c32);
        a3List.add(c33);
        
        //to set data into first customer's card list
        c3.setAccountList(a3List);
      
    return list;
    }
    
    
    //1.2. TO GET ALL CUSTOMERS-----------------------------------------------------------------------------------------
    public List<Customer> getAllCustomers() {
        return list;
        
    }
    
    //1.3. TO CREATE A NEW CUSTOMER WITH MOCK DATA----------------------------------------------------------------------
    
    public Customer addMockCustomer(Customer newCustomer){
        newCustomer.setCustomerCreated(new Date());
        list.add(newCustomer);
        return newCustomer;
    }
    
    //=============================== 2. CUSTOMER METHODS ==============================================================
    
    //2.1. TO GET SPECIFIC CUSTOMER BY IT'S ID--------------------------------------------------------------------------
    public Customer getCustomer(int customerId){
        for(int i = 0; i < list.size(); i++){
            if(customerId==list.get(i).getCustomerId()){
                return list.get(i);
            }
        }
        return null;
    }
    
    
    //2.2. TO GET SPECIFIC CUSTOMER BY IT'S NAME------------------------------------------------------------------------
    public Customer getCustomerbyName(String customerName){
        for(int i = 0; i < list.size(); i++){
            if(customerName.equalsIgnoreCase(list.get(i).getCustomerName())){
                return list.get(i);
            }
        }
        return null;
    }
    
    
    //2.3.  TO CREATE NEW CUSTOMER (inputs from the customer are email and password)------------------------------------
    //customerAddress and customerName are created as empty string so it can be updated with PUT in the next step
    public Customer addCustomer(Customer newCustomer){
        Random rn = new Random();
        int customerId = 100 + rn.nextInt(900);
        newCustomer.setCustomerAddress(" ");
        newCustomer.setCustomerName(" ");
        newCustomer.setCustomerId(customerId);
        newCustomer.setCustomerCreated(new Date());
        list.add(newCustomer);
        return newCustomer;
    }
    
    //2.4.  TO UPDATE ABOVE CREATED CUSTOMER (inputs from the customer are customerName and customerAddress)------------
    public void updateCustomer(int customerId, String customerName, String customerAddress){
        Customer cust = getCustomer(customerId);
            if (customerId==cust.getCustomerId()){
                cust.setCustomerName(customerName);
                cust.setCustomerAddress(customerAddress);
            }
        
    }
    
    
    //2.5. TO CREATE ACCOUNT BY ACCOUNT TYPE AND BY CUSTOMER ID---------------------------------------------------------
    //the method is checking if account with the same accountType already exist, 
    //as customer cannot have two accounts with the same accountType
    //Every account has it's default accountID and accountType
    //Account ID: 10001 for Visa Debit, 10002 for Visa Credit, 10003 for Master Card
    //10004 for Saving Account
    //accountNumber is set as a random number
    //every new customer is getting €5000, it is set as a balance
    //sortCode is default set as "DUB01"
    public void addAccountOnTheList(int customerId, Account newAccount){
        
        if(ifAccountAlreadyExist(customerId, newAccount) == true){
            newAccount = null;
            
        }else{
            Customer cust = getCustomer(customerId);
            List<Account> accountList = cust.getAccountList();
            switch (newAccount.getAccountType()) {
                case "Visa Debit":
                    newAccount.setAccountId(10001);
                    newAccount.setAccountType("Visa Debit");
                    break;
                case "Visa Credit":
                    newAccount.setAccountId(10002);
                    newAccount.setAccountType("Visa Credit");
                    break;
                case "Master Card":
                    newAccount.setAccountId(10003);
                    newAccount.setAccountType("Master Card");
                    break;
                case "Saving Account":
                    newAccount.setAccountId(10004);
                    newAccount.setAccountType("Saving Account");
                    break;
                }
                Random random = new Random();
                int newAccountNumber = 10000000 + random.nextInt(90000000);
                newAccount.setAccNumber(newAccountNumber);
                newAccount.setAccountCreated(new Date());
                newAccount.setBalance(5000.00);
                newAccount.setSortCode("DUB01");
                accountList.add(newAccount);
        }
    }
    
    
    
    //2.6. TO DISPLAY ACCOUNT LIST BY ACCOUNT TYPE AND BY CUSTOMER ID --------------------------------------------------
    public List<Account> displayAccountByTypeAndByCustomerId(int customerId, String accountType){
                Customer cust = getCustomer(customerId);
                List<Account> accountList = cust.getAccountList();
                for(int j = 0; j < accountList.size(); j++){
                    Account account = accountList.get(j);
                    if(accountType.equalsIgnoreCase(account.getAccountType())){
                        return Collections.singletonList(account);
                    }
                }
        return null;
    }
    
  
    //2.6. TO FIND ACCOUNT BY ACCOUNT TYPE AND BY CUSTOMER ID ----------------------------------------------------------
    //NEEDED FOR getBalance METHOD
    public Account findAccountByTypeAndByCustomerId(int customerId, String accountType){
                Customer cust = getCustomer(customerId);
                List<Account> accountList = cust.getAccountList();
                for(int j = 0; j < accountList.size(); j++){
                    Account account = accountList.get(j);
                    if(accountType.equalsIgnoreCase(account.getAccountType())){
                        return account;
                    }
                }
                
        return null;
    }
    
    
    //2.7. METHOD FOR GETTING BALANCE-----------------------------------------------------------------------------------
    public Double getBalance(int customerId, String accountType){
        Account newAccount = findAccountByTypeAndByCustomerId(customerId, accountType);
        if(newAccount==null){
            return null;
        }else{
            Double balance = newAccount.getBalance();
            return balance;
        }
        
    }
    
    //2.8. METHOD FOR WITHDRAWAL TRANSACTION----------------------------------------------------------------------------
    //IT IS CHECKING IF INPUT VALUES ARE VALID AND IF ACCOUNT HAS ENOUGH MONEY FOR TRANSACTION TO BE EXECUTED
    //RULE SET FOR THE ACCOUNTS: WITHDRAWAL CAN ONLY BE EXECUTED FROM VISA DEBIT ACCOUNT
    public String withdrawal(int customerId, String accountType, int accNumber, Transaction newTransaction){
      Account account = findAccount(customerId, accountType, accNumber);
        if (account == null) {
            return "Transaction cannot be executed";
        }
        
        if (!hasEnoughMoney(account, newTransaction.getAmount())) {
            return "Transaction cannot be executed. There is no enough money.";
        }

        if ("Visa Credit".equalsIgnoreCase(account.getAccountType())) {
            return "Cannot transfer from Visa Credit account";
        }
        
        if ("Master Card".equalsIgnoreCase(account.getAccountType())) {
            return "Cannot transfer from Visa Credit account";
        }
        
        if ("Saving Account".equalsIgnoreCase(account.getAccountType())) {
            return "Cannot transfer from Saving account";
        }

        String result = transferMoneyFromOutgoing(account, newTransaction.getAmount());
        return result;
    }

        
    
    //2.9. METHOD FOR LODGEMENT TRANSACTION-----------------------------------------------------------------------------
    //IT IS CHECKING IF INPUT VALUES ARE VALID 
    public String lodgement(int customerId, String accountType, int accNumber, Transaction newTransaction){
       Account account = findAccount(customerId, accountType, accNumber);
        if (account == null) {
            return "Transaction cannot be executed";
        }
        transferMoneyToIncoming(account, newTransaction.getAmount());
        return "Transaction was successful. New balance is: " + account.getBalance();
    }
    
    
    
    //2.9. METHOD FOR TRANSFER TRANSACTION BETWEEN TWO ACCOUNTS OF THE SAME CUSTOMER------------------------------------
    //IT IS CHECKING IF INPUT VALUES ARE VALID AND IF OUTGOING ACCOUNT HAS ENOUGH MONEY FOR TRANSACTION TO BE EXECUTED
    //RULE SET FOR THE ACCOUNTS: WITHDRAWAL CAN ONLY BE EXECUTED FROM VISA DEBIT ACCOUNT
    public String transferBetweenAccounts(int customerId, String accountTypeOut, int accNumberOut,
                                          String accountTypeIn, int accNumberIn, Transaction newTransaction) {

        Account outgoingAccount = findAccount(customerId, accountTypeOut, accNumberOut);
        Account incomingAccount = findAccount(customerId, accountTypeIn, accNumberIn);

        if (outgoingAccount == null || incomingAccount == null) {
            return "Transaction cannot be executed";
        }

        if (!hasEnoughMoney(outgoingAccount, newTransaction.getAmount())) {
            return "Transaction cannot be executed. There is no enough money.";
        }
        
        if ("Visa Credit".equalsIgnoreCase(outgoingAccount.getAccountType())) {
            return "Cannot transfer from Visa Credit account";
        }
        
        if ("Master Card".equalsIgnoreCase(outgoingAccount.getAccountType())) {
            return "Cannot transfer from Master Card account";
        }
        
        if ("Saving Account".equalsIgnoreCase(outgoingAccount.getAccountType())) {
            return "Cannot transfer from Saving account";
        }
        
        String result = transferMoneyFromOutgoing(outgoingAccount, newTransaction.getAmount());
        transferMoneyToIncoming(incomingAccount, newTransaction.getAmount());
        return result;
    }
    
    
    
    //2.9. METHOD FOR TRANSFER TRANSACTION BETWEEN TWO ACCOUNTS OF DIFFERENT CUSTOMERS----------------------------------
    //IT IS CHECKING IF INPUT VALUES ARE VALID AND IF OUTGOING ACCOUNT HAS ENOUGH MONEY FOR TRANSACTION TO BE EXECUTED
    //RULE SET FOR THE ACCOUNTS: WITHDRAWAL CAN ONLY BE EXECUTED FROM VISA DEBIT ACCOUNT
    public String transferBetweenCustomers(int customerIdOut, String accountTypeOut, int accNumberOut,
                                           String customerNameIn, int accNumberIn, 
                                           Transaction newTransaction) {

        Account outgoingAccount = findAccount(customerIdOut, accountTypeOut, accNumberOut);
        Account incomingAccount = findReceiversAccount(customerNameIn, accNumberIn);

        if (outgoingAccount == null || incomingAccount == null) {
            return "Transaction cannot be executed";
        }

        if (!hasEnoughMoney(outgoingAccount, newTransaction.getAmount())) {
            return "Transaction cannot be executed. There is no enough money.";
        }

        if ("Visa Credit".equalsIgnoreCase(outgoingAccount.getAccountType())) {
            return "Cannot transfer from Visa Credit account";
        }
        
        if ("Master Card".equalsIgnoreCase(outgoingAccount.getAccountType())) {
            return "Cannot transfer from Master card account";
        }
        
        if ("Saving Account".equalsIgnoreCase(outgoingAccount.getAccountType())) {
            return "Cannot transfer from Saving account";
        }

        String result = transferMoneyFromOutgoing(outgoingAccount, newTransaction.getAmount());
        transferMoneyToIncoming(incomingAccount, newTransaction.getAmount());
        return result;
    }

    
    
    //2.10.METHOD NEEDED FOR TRANSFER TRANSACTIONS METHODS--------------------------------------------------------------
    //IT IS CALCULATING BALANCE AFTER TRANSACTION IS EXECUTED AND IS SETTING TRANSACTION DESCRIPTION AND CREATING TRANSACTION
    //FOR THE INCOMING ACCOUNT
    private void transferMoneyToIncoming(Account account, double amount) {
        double postBalance = account.getBalance() + amount;
        account.setBalance(postBalance);
        createTransaction(account, amount, "Lodgement");
    }
    
    //2.11.METHOD NEEDED FOR TRANSFER TRANSACTIONS METHODS--------------------------------------------------------------
    //IT IS CALCULATING BALANCE AFTER TRANSACTION IS EXECUTED AND IS SETTING TRANSACTION DESCRIPTION AND CREATING TRANSACTION
    //FOR THE OUTGOING ACCOUNT
    private String transferMoneyFromOutgoing(Account account, double amount) {
        double postBalance = account.getBalance() - amount;
        account.setBalance(postBalance);
        createTransaction(account, amount, "Withdrawal");
        return "Transaction was successful. New balance is: " + account.getBalance();
    }
    
    
    //2.12. METHOD NEEDED FOR TRANSACTIONS, IT CREATES TRANSACTION------------------------------------------------------
    private void createTransaction(Account account, double amount, String description) {
        List<Transaction> tList = account.getTransactionList();
        Transaction trans = new Transaction();
        //set transaction number (assigned as a random number)
        Random random = new Random();
        int transactionNumber = 100000 + random.nextInt(900000);
        findAndSetTransactionType(account, trans);
        trans.setTransactionNumber(transactionNumber);
        trans.setDescription(description);
        trans.setPostBalance(account.getBalance());
        trans.setAmount(amount);
        trans.setTransactionCreated(new Date());
        tList.add(trans);
    }

    //2.13. METHOD NEEDED FOR TRANSACTIONS, RULES FOR SETTING TRANSACTION TYPE------------------------------------------
    private void findAndSetTransactionType(Account account, Transaction trans) {
        if (account.getAccountType().equalsIgnoreCase("Visa Debit")) {
            trans.setTransactionType("Visa Debit");
        } else if (account.getAccountType().equalsIgnoreCase("Visa Credit")) {
            trans.setTransactionType("Visa Credit");
        } else if (account.getAccountType().equalsIgnoreCase("Master Card")) {
            trans.setTransactionType("Master Card");
        } else {
            trans.setTransactionType("Saving Account");
        }
    }
    
    //2.14. METHOD NEEDED FOR TRANSACTIONS, CHECKING IF ACCOUNT HAS ENOUGH MONEY FOR WITHDRAWAL TRANSFER TRANSACTION----
    private boolean hasEnoughMoney(Account outgoingAccount, double amount) {
        return outgoingAccount.getBalance() >= amount;
    }
    
    //2.15. METHOD FOR FINDING ACCOUNT NEEDED FOR TRANSACTIONS METHODS
    private Account findAccount(int customerId, String accountType, int accNumber) {
        Customer customer = getCustomer(customerId);
                List<Account> accountList = customer.getAccountList();
                for (int j = 0; j < accountList.size(); j++) {
                    Account account = accountList.get(j);
                    if (accountType.equalsIgnoreCase(account.getAccountType())
                            && accNumber == account.getAccNumber()) {
                        return account;
                    }
                }
         
        return null;
    }
    
    //2.16. METHOD NEEDED FOR TRANSACTION TO ANOTHER CUSTOMERS ACCOUNT--------------------------------------------------
    //IT IS FINDING CUSTOMER'S/RECEIVER'S ACCOUNT
    private Account findReceiversAccount(String customerName, int accNumber) {
        Customer customer = getCustomerbyName(customerName);
                List<Account> accountList = customer.getAccountList();
                for (int j = 0; j < accountList.size(); j++) {
                    Account account = accountList.get(j);
                    if (accNumber == account.getAccNumber()) {
                        return account;
                    }
                }
         
        return null;
    }
    
    //2.17. METHOD NEEDED FOR CREATING NEW ACCOUNT----------------------------------------------------------------------
    //IT IS CHECKING IF ACCOUNT BY ACCOUNT TYPE ALREDY EXIST ON ACCOUNT LIST
    private boolean ifAccountAlreadyExist(int customerId, Account newAccount){
        Customer cust = getCustomer(customerId);
        List<Account> accountList = cust.getAccountList();
        for(int i = 0; i < accountList.size(); i++){
            Account account = accountList.get(i);
            if(account.getAccountType().equalsIgnoreCase(newAccount.getAccountType())){
                return true;
            }
        }
        return false;
    }
    
    
    //2.18. METHOD FOR GETTING ALL TRANSACTIONS BY ACCOUNT TYPE FOR CUSTOMER BY IT'S CUSTOMER ID------------------------
    public List getTransactions(int customerId, String accountType){
        for(int i = 0; i < list.size(); i++){
            if(customerId==list.get(i).getCustomerId()){
                Customer customer = list.get(i);
                List<Account> accountList = customer.getAccountList();
                for(int j = 0; j < accountList.size(); j++){
                    if(accountType.equalsIgnoreCase(list.get(i).getAccountList().get(j).getAccountType())){
                        List<Transaction> tList = accountList.get(j).getTransactionList();
                        return tList;
                        }  
                } 
            } 
        }              
                            
        return null;
    }
    
    
    
}
