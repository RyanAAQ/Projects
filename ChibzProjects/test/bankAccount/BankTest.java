package bankAccount;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

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
        assertEquals(BigDecimal.ZERO, bank.checkBalance(accountNumber, "1234"));
    }

    @Test
    public void depositIncreasesBalance() {
        bank.deposit(accountNumber, new BigDecimal("1000"));
        assertEquals(new BigDecimal("1000"), bank.checkBalance(accountNumber, "1234"));
    }

    @Test
    public void depositToAccountThatDoesNotExistThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> bank.deposit("9999", new BigDecimal("500")));
    }

    @Test
    public void withdrawingChangesTheBalance() {
        bank.deposit(accountNumber, new BigDecimal("500"));
        bank.withdraw(accountNumber, new BigDecimal("200"), "1234");
        assertEquals(new BigDecimal("300"), bank.checkBalance(accountNumber, "1234"));
    }

    @Test
    public void withdrawingWithWrongPinThrowsException() {
        bank.deposit(accountNumber, new BigDecimal("500"));
        assertThrows(IllegalArgumentException.class, () -> bank.withdraw(accountNumber, new BigDecimal("200"), "0000"));
    }

    @Test
    public void withdrawingMoreThanBalanceThrowsException() {
        bank.deposit(accountNumber, new BigDecimal("100"));
        assertThrows(IllegalArgumentException.class, () -> bank.withdraw(accountNumber, new BigDecimal("500"), "1234"));
    }

    @Test
    public void transferSendsMoneyBetweenAccounts() {
        Account me = bank.registerCustomer("Ariyo", "Quadri", "5678");
        bank.deposit(accountNumber, new BigDecimal("1000"));
        bank.transfer(accountNumber, me.getNumber(), new BigDecimal("400"), "1234");
        assertEquals(new BigDecimal("600"), bank.checkBalance(accountNumber, "1234"));
        assertEquals(new BigDecimal("400"), bank.checkBalance(me.getNumber(), "5678"));
    }

    @Test
    public void transferWithWrongPinThrowsException() {
        Account second = bank.registerCustomer("John", "Doe", "5678");
        bank.deposit(accountNumber, new BigDecimal("1000"));
        assertThrows(IllegalArgumentException.class, () -> bank.transfer(accountNumber, second.getNumber(), new BigDecimal("400"), "0000"));
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
        bank.deposit(accountNumber, new BigDecimal("500"));
        bank.deposit(bro.getNumber(), new BigDecimal("300"));
        assertEquals(new BigDecimal("500"), bank.checkBalance(accountNumber, "1234"));
        assertEquals(new BigDecimal("300"), bank.checkBalance(bro.getNumber(), "4321"));
    }
}
