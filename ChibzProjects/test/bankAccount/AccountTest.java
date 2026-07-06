package bankAccount;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

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
        assertEquals(BigDecimal.ZERO, account.getBalance());
    }

    @Test
    public void depositingIncreasesBalance() {
        account.deposit(new BigDecimal("1000"));
        assertEquals(new BigDecimal("1000"), account.getBalance());
    }

    @Test
    public void depositingWithNegativeAmountThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> account.deposit(new BigDecimal("-100")));
    }

    @Test
    public void depositWithZeroAmountThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> account.deposit(BigDecimal.ZERO));
    }

    @Test
    public void moneyIsAddedWhenDepositing() {
        account.deposit(new BigDecimal("100"));
        account.deposit(new BigDecimal("200"));
        assertEquals(new BigDecimal("300"), account.getBalance());
    }

    @Test
    public void withdrawingReducesTheBalance() {
        account.deposit(new BigDecimal("500"));
        account.withdraw(new BigDecimal("200"), "1234");
        assertEquals(new BigDecimal("300"), account.getBalance());
    }

    @Test
    public void withdrawingWithIncorrectPinThrowsException() {
        account.deposit(new BigDecimal("500"));
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(new BigDecimal("100"), "9999"));
    }

    @Test
    public void withdrawingMoreThanBalanceThrowsException() {
        account.deposit(new BigDecimal("100"));
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(new BigDecimal("500"), "1234"));
    }

    @Test
    public void withdrawingNegativeAmountThrowsException() {
        account.deposit(new BigDecimal("200"));
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(new BigDecimal("-50"), "1234"));
    }

    @Test
    public void withdrawingZeroAmountThrowsException() {
        account.deposit(new BigDecimal("200"));
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(BigDecimal.ZERO, "1234"));
    }

    @Test
    public void checkBalanceWithWrongPinThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> account.checkBalance("2345"));
    }

    @Test
    public void depositAndWithdrawWorkCorrectly() {
        account.deposit(new BigDecimal("1000"));
        account.withdraw(new BigDecimal("400"), "1234");
        assertEquals(new BigDecimal("600"), account.getBalance());
    }
}
