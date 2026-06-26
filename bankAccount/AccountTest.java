package bankAccount;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {

    private Account account;

    @BeforeEach
    public void setUp() {
        account = new Account();
    }

    @Test
    public void newAccountBalanceIsZero() {
        assertEquals(0.0, account.getBalance());
    }

    @Test
    public void depositingIncreasesBalance() {
        account.deposit(1000);
        assertEquals(1000.0, account.getBalance());
    }

    @Test
    public void depositingWithNegativeAmountDoesNotChangeTheBalance() {
        account.deposit(-100);
        assertEquals(0.0, account.getBalance());
    }

    @Test
    public void depositWithZeroAmountDoesNotChangeTheBalance() {
        account.deposit(0);
        assertEquals(0.0, account.getBalance());
    }

    @Test
    public void moneyIsAddedWhenDepositing() {
        account.deposit(100);
        account.deposit(200);
        assertEquals(300.0, account.getBalance());
    }

    @Test
    public void withdrawingReducesTheBalance() {
        account.deposit(500);
        account.withdraw(200, 1234);
        assertEquals(300.0, account.getBalance());
    }

    @Test
    public void withdrawingWithIncorrectPinThrowsException() {
        account.deposit(500);
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(100, 99));
    }

    @Test
    public void withdrawingMoreThanTheBalanceDoesNotChangeTheBalance() {
        account.deposit(100);
        account.withdraw(500, 1234);
        assertEquals(100.0, account.getBalance());
    }

    @Test
    public void withdrawingANegativeAmountDoesNotChangeTheBalance() {
        account.deposit(200);
        account.withdraw(-50, 1234);
        assertEquals(200.0, account.getBalance());
    }

    @Test
    public void withdrawingZeroAmountDoesNotChangeTheBalance() {
        account.deposit(200);
        account.withdraw(0, 1234);
        assertEquals(200.0, account.getBalance());
    }

    @Test
    public void depositAndWithdrawWorkCorrectly() {
        account.deposit(1000);
        account.withdraw(400, 1234);
        assertEquals(600.0, account.getBalance());
    }
}
