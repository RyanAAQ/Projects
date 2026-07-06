package bankAccount;

import java.math.BigDecimal;
import java.util.Scanner;

public class BankApp {
    private static Bank bank = new Bank("ChibzBank");
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to " + bank.getName());

        while (true) {
            System.out.println("\n1. Register");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Check Balance");
            System.out.println("6. Remove Account");
            System.out.println("7. Exit");
            System.out.print("Choose: ");

            String choice = input.nextLine().trim();

            switch (choice) {
                case "1" -> register();
                case "2" -> deposit();
                case "3" -> withdraw();
                case "4" -> transfer();
                case "5" -> checkBalance();
                case "6" -> removeAccount();
                case "7" -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private static void register() {
        System.out.print("First name: ");
        String first = input.nextLine().trim();
        System.out.print("Last name: ");
        String last = input.nextLine().trim();
        System.out.print("4-digit PIN: ");
        String pin = input.nextLine().trim();

        try {
            Account account = bank.registerCustomer(first, last, pin);
            System.out.println("Account created. Your account number is: " + account.getNumber());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void deposit() {
        System.out.print("Account number: ");
        String number = input.nextLine().trim();
        System.out.print("Amount: ");
        try {
            BigDecimal amount = new BigDecimal(input.nextLine().trim());
            bank.deposit(number, amount);
            System.out.println("Deposit successful.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void withdraw() {
        System.out.print("Account number: ");
        String number = input.nextLine().trim();
        System.out.print("Amount: ");
        BigDecimal amount = new BigDecimal(input.nextLine().trim());
        System.out.print("PIN: ");
        String pin = input.nextLine().trim();

        try {
            bank.withdraw(number, amount, pin);
            System.out.println("Withdrawal successful.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void transfer() {
        System.out.print("Your account number: ");
        String sender = input.nextLine().trim();
        System.out.print("Recipient account number: ");
        String receiver = input.nextLine().trim();
        System.out.print("Amount: ");
        BigDecimal amount = new BigDecimal(input.nextLine().trim());
        System.out.print("PIN: ");
        String pin = input.nextLine().trim();

        try {
            bank.transfer(sender, receiver, amount, pin);
            System.out.println("Transfer successful.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void checkBalance() {
        System.out.print("Account number: ");
        String number = input.nextLine().trim();
        System.out.print("PIN: ");
        String pin = input.nextLine().trim();

        try {
            BigDecimal balance = bank.checkBalance(number, pin);
            System.out.println("Balance: " + balance);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void removeAccount() {
        System.out.print("Account number: ");
        String number = input.nextLine().trim();
        System.out.print("PIN: ");
        String pin = input.nextLine().trim();

        try {
            bank.removeAccount(number, pin);
            System.out.println("Account removed.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
