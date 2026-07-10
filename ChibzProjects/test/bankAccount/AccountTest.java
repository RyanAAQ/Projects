package bankAccount;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Account")
public class AccountTest {

    private Account account;

    @BeforeEach
    public void setUp() {
        account = new Account("Ryan", "Quadri", "1001", "1234");
    }

    // --- Creation ---

    @Test
    @DisplayName("full name is first name and last name joined with a space")
    public void accountNameIsFirstAndLastNameCombined() {
        assertEquals("Ryan Quadri", account.getName());
    }

    @Test
    @DisplayName("account number is stored as provided")
    public void accountNumberIsStored() {
        assertEquals("1001", account.getNumber());
    }

    @Test
    @DisplayName("new account starts with a zero balance")
    public void newAccountBalanceIsZero() {
        assertEquals(0.0, account.getBalance());
    }

    @Test
    @DisplayName("creating an account with a pin shorter than 4 digits throws IllegalArgumentException")
    public void shortPinOnCreationThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Account("Ryan", "Quadri", "1001", "12"));
    }

    @Test
    @DisplayName("creating an account with a pin longer than 4 digits throws IllegalArgumentException")
    public void longPinOnCreationThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Account("Ryan", "Quadri", "1001", "12345"));
    }

    @Test
    @DisplayName("creating an account with a null pin throws IllegalArgumentException")
    public void nullPinOnCreationThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Account("Ryan", "Quadri", "1001", null));
    }

    // --- isValidPin ---

    @Test
    @DisplayName("isValidPin returns true for exactly 4 characters")
    public void isValidPinReturnsTrueForFourCharPin() {
        assertTrue(account.isValidPin("1234"));
    }

    @Test
    @DisplayName("isValidPin returns false for null")
    public void isValidPinReturnsFalseForNull() {
        assertFalse(account.isValidPin(null));
    }

    @Test
    @DisplayName("isValidPin returns false for a pin with fewer than 4 characters")
    public void isValidPinReturnsFalseForShortPin() {
        assertFalse(account.isValidPin("12"));
    }

    @Test
    @DisplayName("isValidPin returns false for a pin with more than 4 characters")
    public void isValidPinReturnsFalseForLongPin() {
        assertFalse(account.isValidPin("12345"));
    }

    // --- Deposit ---

    @Test
    @DisplayName("depositing a valid amount increases the balance by that amount")
    public void depositIncreasesBalance() {
        account.deposit(1000.0);

        assertEquals(1000.0, account.getBalance());
    }

    @Test
    @DisplayName("multiple deposits accumulate correctly")
    public void multipleDepositsAccumulate() {
        account.deposit(100.0);
        account.deposit(200.0);

        assertEquals(300.0, account.getBalance());
    }

    @Test
    @DisplayName("depositing a negative amount throws IllegalArgumentException")
    public void depositNegativeAmountThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> account.deposit(-100.0));
    }

    @Test
    @DisplayName("depositing zero throws IllegalArgumentException")
    public void depositZeroThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> account.deposit(0.0));
    }

    // --- Withdraw ---

    @Test
    @DisplayName("withdrawing a valid amount reduces the balance by that amount")
    public void withdrawReducesBalance() {
        account.deposit(500.0);

        account.withdraw(200.0, "1234");

        assertEquals(300.0, account.getBalance());
    }

    @Test
    @DisplayName("withdrawing with the wrong pin throws IllegalArgumentException")
    public void withdrawWithWrongPinThrowsException() {
        account.deposit(500.0);

        assertThrows(IllegalArgumentException.class,
                () -> account.withdraw(100.0, "9999"));
    }

    @Test
    @DisplayName("withdrawing more than the available balance throws IllegalArgumentException")
    public void withdrawMoreThanBalanceThrowsException() {
        account.deposit(100.0);

        assertThrows(IllegalArgumentException.class,
                () -> account.withdraw(500.0, "1234"));
    }

    @Test
    @DisplayName("withdrawing a negative amount throws IllegalArgumentException")
    public void withdrawNegativeAmountThrowsException() {
        account.deposit(200.0);

        assertThrows(IllegalArgumentException.class,
                () -> account.withdraw(-50.0, "1234"));
    }

    @Test
    @DisplayName("withdrawing zero throws IllegalArgumentException")
    public void withdrawZeroThrowsException() {
        account.deposit(200.0);

        assertThrows(IllegalArgumentException.class,
                () -> account.withdraw(0.0, "1234"));
    }

    // --- Check Balance ---

    @Test
    @DisplayName("checkBalance with the correct pin returns the current balance")
    public void checkBalanceWithCorrectPinReturnsBalance() {
        account.deposit(300.0);

        assertEquals(300.0, account.checkBalance("1234"));
    }

    @Test
    @DisplayName("checkBalance with the wrong pin throws IllegalArgumentException")
    public void checkBalanceWithWrongPinThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> account.checkBalance("2345"));
    }

    // --- Combined ---

    @Test
    @DisplayName("balance reflects the net of deposits and withdrawals")
    public void balanceReflectsNetOfDepositsAndWithdrawals() {
        account.deposit(1000.0);
        account.withdraw(400.0, "1234");

        assertEquals(600.0, account.getBalance());
    }
}
