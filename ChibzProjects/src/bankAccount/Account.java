package bankAccount;

import java.math.BigDecimal;

public class Account {
    private String name;
    private BigDecimal balance;
    private String pin;
    private String number;

    public Account(String firstName, String lastName, String number, String pin) {
        if (!isValidPin(pin)) {
            throw new IllegalArgumentException("Pin must be exactly 4 digits");
        }
        this.name = firstName + " " + lastName;
        this.number = number;
        this.pin = pin;
        this.balance = BigDecimal.ZERO;
    }

    public boolean isValidPin(String pin) {
        return pin != null && pin.length() == 4;
    }

    public void deposit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
        this.balance = this.balance.add(amount);
    }

    public void withdraw(BigDecimal amount, String pin) {
        if (!this.pin.equals(pin)) {
            throw new IllegalArgumentException("Incorrect pin");
        }
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
        if (amount.compareTo(balance) > 0) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        this.balance = this.balance.subtract(amount);
    }

    public BigDecimal checkBalance(String pin) {
        if (!this.pin.equals(pin)) {
            throw new IllegalArgumentException("Incorrect pin");
        }
        return balance;
    }

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getBalance() {
        return balance;
    }
}
