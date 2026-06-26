package bankAccount;

public class Account {
    private double balance = 0.0;

    public void deposit(int amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public void withdraw(int amount, int pin) {
        if (pin < 1000 || pin > 9999) {
            throw new IllegalArgumentException("Incorrect pin");
        }
        if (amount > 0 && amount <= balance) {
            balance -= amount;
        }
    }

    public double getBalance() {
        return balance;
    }
}
