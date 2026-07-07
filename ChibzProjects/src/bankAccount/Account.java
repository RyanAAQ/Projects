package bankAccount;

public class Account {
    private String name;
    private double balance;
    private String pin;
    private String number;

    public Account(String firstName, String lastName, String number, String pin) {
        if (!isValidPin(pin)) {
            throw new IllegalArgumentException("Pin must be exactly 4 digits");
        }
        this.name = firstName + " " + lastName;
        this.number = number;
        this.pin = pin;
        this.balance = 0;
    }

    public boolean isValidPin(String pin) {
        return pin != null && pin.length() == 4;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
        this.balance += amount;
    }

    public void withdraw(double amount, String pin) {
        if (!this.pin.equals(pin)) {
            throw new IllegalArgumentException("Incorrect pin");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        this.balance -= amount;
    }

    public double checkBalance(String pin) {
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

    public double getBalance() {
        return balance;
    }
}
