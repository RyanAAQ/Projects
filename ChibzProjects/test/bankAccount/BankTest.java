package bankAccount;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BankTest {
    private Bank bank;
    private String accountNumber;

    @BeforeEach
    public void setUp() {
        bank = new Bank("MyBank");
        Account account = bank.registerCustomer("Ryan", "Ariyo", "1234");
        accountNumber = account.getNumber();
    }

    @Test
    public void registerCustomerCreatesAccount() {
        assertNotNull(bank.findAccount(accountNumber));
    }

    @Test
    public void newAccountBalanceIsZero() {
        assertEquals(0.0, bank.checkBalance(accountNumber, "1234"));
    }

    @Test
    public void depositIncreasesBalance() {
        bank.deposit(accountNumber, 1000.0);
        assertEquals(1000.0, bank.checkBalance(accountNumber, "1234"));
    }

    @Test
    public void depositToAccountThatDoesNotExistThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> bank.deposit("9999", 500.0));
    }

    @Test
    public void withdrawingChangesTheBalance() {
        bank.deposit(accountNumber, 500.0);
        bank.withdraw(accountNumber, 200.0, "1234");
        assertEquals(300.0, bank.checkBalance(accountNumber, "1234"));
    }

    @Test
    public void withdrawingWithWrongPinThrowsException() {
        bank.deposit(accountNumber, 500.0);
        assertThrows(IllegalArgumentException.class, () -> bank.withdraw(accountNumber, 200.0, "0000"));
    }

    @Test
    public void withdrawingMoreThanBalanceThrowsException() {
        bank.deposit(accountNumber, 100.0);
        assertThrows(IllegalArgumentException.class, () -> bank.withdraw(accountNumber, 500.0, "1234"));
    }

    @Test
    public void transferSendsMoneyBetweenAccounts() {
        Account me = bank.registerCustomer("Ariyo", "Quadri", "5678");
        bank.deposit(accountNumber, 1000.0);
        bank.transfer(accountNumber, me.getNumber(), 400.0, "1234");
        assertEquals(600.0, bank.checkBalance(accountNumber, "1234"));
        assertEquals(400.0, bank.checkBalance(me.getNumber(), "5678"));
    }

    @Test
    public void transferWithWrongPinThrowsException() {
        Account second = bank.registerCustomer("John", "Doe", "5678");
        bank.deposit(accountNumber, 1000.0);
        assertThrows(IllegalArgumentException.class, () -> bank.transfer(accountNumber, second.getNumber(), 400.0, "0000"));
    }

    @Test
    public void removeAccountDeletesTheAccount() {
        bank.removeAccount(accountNumber, "1234");
        assertNull(bank.findAccount(accountNumber));
    }

    @Test
    public void removeAccountWithWrongPinThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> bank.removeAccount(accountNumber, "0000"));
    }

    @Test
    public void findAccountThatDoesNotExistReturnsNull() {
        assertNull(bank.findAccount("9999"));
    }

    @Test
    public void multipleAccountsAreTrackedSeparately() {
        Account bro = bank.registerCustomer("Ariyo", "Quayyum", "4321");
        bank.deposit(accountNumber, 500.0);
        bank.deposit(bro.getNumber(), 300.0);
        assertEquals(500.0, bank.checkBalance(accountNumber, "1234"));
        assertEquals(300.0, bank.checkBalance(bro.getNumber(), "4321"));
    }
}
