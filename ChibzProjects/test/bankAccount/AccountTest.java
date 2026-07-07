package bankAccount;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {

    private Account account;

    @BeforeEach
    public void setUp() {
        account = new Account("Ryan", "Quadri", "1001", "1234");
    }

    @Test
    public void accountNameIsFirstAndLastNameCombined() {
        assertEquals("Ryan Quadri", account.getName());
    }

    @Test
    public void invalidPinOnCreationThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new Account("Ryan", "Quadri", "1001", "12"));
    }

    @Test
    public void newAccountBalanceIsZero() {
        assertEquals(0.0, account.getBalance());
    }

    @Test
    public void depositingIncreasesBalance() {
        account.deposit(1000.0);
        assertEquals(1000.0, account.getBalance());
    }

    @Test
    public void depositingWithNegativeAmountThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> account.deposit(-100.0));
    }

    @Test
    public void depositWithZeroAmountThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> account.deposit(0.0));
    }

    @Test
    public void moneyIsAddedWhenDepositing() {
        account.deposit(100.0);
        account.deposit(200.0);
        assertEquals(300.0, account.getBalance());
    }

    @Test
    public void withdrawingReducesTheBalance() {
        account.deposit(500.0);
        account.withdraw(200.0, "1234");
        assertEquals(300.0, account.getBalance());
    }

    @Test
    public void withdrawingWithIncorrectPinThrowsException() {
        account.deposit(500.0);
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(100.0, "9999"));
    }

    @Test
    public void withdrawingMoreThanBalanceThrowsException() {
        account.deposit(100.0);
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(500.0, "1234"));
    }

    @Test
    public void withdrawingNegativeAmountThrowsException() {
        account.deposit(200.0);
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(-50.0, "1234"));
    }

    @Test
    public void withdrawingZeroAmountThrowsException() {
        account.deposit(200.0);
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(0.0, "1234"));
    }

    @Test
    public void checkBalanceWithWrongPinThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> account.checkBalance("2345"));
    }

    @Test
    public void depositAndWithdrawWorkCorrectly() {
        account.deposit(1000.0);
        account.withdraw(400.0, "1234");
        assertEquals(600.0, account.getBalance());
    }
}
