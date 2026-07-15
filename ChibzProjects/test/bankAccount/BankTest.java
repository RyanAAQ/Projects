package bankAccount;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
    public void getBankNameReturnsCorrectName() {
        assertEquals("MyBank", bank.getName());
    }

    @Test
    public void findAccountThatDoesNotExistReturnsNull() {
        assertNull(bank.findAccount("9999"));
    }

    @Test
    public void depositingIncreasesBalanceInTheAccount() {
        bank.deposit(accountNumber, 1000.0);
        assertEquals(1000.0, bank.checkBalance(accountNumber, "1234"));
    }

    @Test
    public void depositingToNonExistentAccountThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> bank.deposit("9999", 500.0));
    }

    @Test
    public void withdrawReducesBalance() {
        bank.deposit(accountNumber, 500.0);
        bank.withdraw(accountNumber, 200.0, "1234");
        assertEquals(300.0, bank.checkBalance(accountNumber, "1234"));
    }

    @Test
    public void withdrawWithWrongPinThrowsException() {
        bank.deposit(accountNumber, 500.0);
        assertThrows(IllegalArgumentException.class, () -> bank.withdraw(accountNumber, 200.0, "0000"));
    }

    @Test
    public void withdrawMoreThanBalanceThrowsException() {
        bank.deposit(accountNumber, 100.0);
        assertThrows(IllegalArgumentException.class, () -> bank.withdraw(accountNumber, 500.0, "1234"));
    }

    @Test
    public void withdrawFromNonExistentAccountThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> bank.withdraw("9999", 100.0, "1234"));
    }

    @Test
    public void transferSendsMoneyBetweenAccounts() {
        Account receiver = bank.registerCustomer("Ariyo", "Quadri", "5678");
        bank.deposit(accountNumber, 1000.0);
        bank.transfer(accountNumber, receiver.getNumber(), 400.0, "1234");
        assertEquals(600.0, bank.checkBalance(accountNumber, "1234"));
        assertEquals(400.0, bank.checkBalance(receiver.getNumber(), "5678"));
    }

    @Test
    public void transferWithWrongPinThrowsException() {
        Account receiver = bank.registerCustomer("John", "Doe", "5678");
        bank.deposit(accountNumber, 1000.0);
        assertThrows(IllegalArgumentException.class, () -> bank.transfer(accountNumber, receiver.getNumber(), 400.0, "0000"));
    }

    @Test
    public void transferFromNonExistentSenderThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> bank.transfer("9999", accountNumber, 100.0, "1234"));
    }

    @Test
    public void transferToNonExistentReceiverThrowsException() {
        bank.deposit(accountNumber, 500.0);

        assertThrows(IllegalArgumentException.class,
                () -> bank.transfer(accountNumber, "9999", 100.0, "1234"));
    }

    @Test
    public void checkBalanceForNonExistentAccountThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> bank.checkBalance("9999", "1234"));
    }

    @Test
    public void removeAccountDeletesIt() {
        bank.removeAccount(accountNumber, "1234");
        assertNull(bank.findAccount(accountNumber));
    }

    @Test
    public void removeAccountWithWrongPinThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> bank.removeAccount(accountNumber, "0000"));
    }

    @Test
    public void removeNonExistentAccountThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> bank.removeAccount("9999", "1234"));
    }

    @Test
    public void multipleAccountsAreTrackedSeparately() {
        Account second = bank.registerCustomer("Ariyo", "Quayyum", "4321");
        bank.deposit(accountNumber, 500.0);
        bank.deposit(second.getNumber(), 300.0);

        assertEquals(500.0, bank.checkBalance(accountNumber, "1234"));
        assertEquals(300.0, bank.checkBalance(second.getNumber(), "4321"));
    }
}
