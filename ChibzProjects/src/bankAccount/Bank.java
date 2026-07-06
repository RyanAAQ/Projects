package bankAccount;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Bank {
    private String name;
    private ArrayList<Account> accounts = new ArrayList<>();
    private int nextAccountNumber = 1001;

    public Bank(String name) {
        this.name = name;
    }

    public boolean accountExists(String accountNumber) {
        return findAccount(accountNumber) != null;
    }

    public Account findAccount(String accountNumber) {
        for (Account account : accounts) {
            if (account.getNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    public Account registerCustomer(String firstName, String lastName, String pin) {
        String accountNumber = String.valueOf(nextAccountNumber);
        Account account = new Account(firstName, lastName, accountNumber, pin);
        accounts.add(account);
        nextAccountNumber++;
        return account;
    }

    public void removeAccount(String accountNumber, String pin) {
        Account account = findAccount(accountNumber);
        if (account == null) {
            throw new IllegalArgumentException("Account not found");
        }
        account.checkBalance(pin);
        accounts.remove(account);
    }

    public void deposit(String accountNumber, BigDecimal amount) {
        Account account = findAccount(accountNumber);
        if (account == null) {
            throw new IllegalArgumentException("Account not found");
        }
        account.deposit(amount);
    }

    public void withdraw(String accountNumber, BigDecimal amount, String pin) {
        Account account = findAccount(accountNumber);
        if (account == null) {
            throw new IllegalArgumentException("Account not found");
        }
        account.withdraw(amount, pin);
    }

    public void transfer(String myAccountNumber, String myGuyAccountNumber, BigDecimal amount, String pin) {
        Account mine = findAccount(myAccountNumber);
        Account myGuy = findAccount(myGuyAccountNumber);
        if (mine == null || myGuy == null) {
            throw new IllegalArgumentException("Account not found");
        }
        mine.withdraw(amount, pin);
        myGuy.deposit(amount);
    }

    public BigDecimal checkBalance(String accountNumber, String pin) {
        Account account = findAccount(accountNumber);
        if (account == null) {
            throw new IllegalArgumentException("Account not found");
        }
        return account.checkBalance(pin);
    }

    public String getName() {
        return name;
    }
}
