package bankAccount;

import java.util.Scanner;

public class BankApp {
    static CentralBank banks = new CentralBank();
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        banks.addBank(new Bank("ChibzBank"));
        banks.addBank(new Bank("GTBbank"));

        System.out.println("=== Welcome to the Greatest Banking System To Ever Exist Bruh ===");
        System.out.println("Available banks: ChibzBank, GTBBank");

        while (true) {
            System.out.println("\n1. Register");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Intra Bank Transfer");
            System.out.println("5. Inter Bank Transfer");
            System.out.println("6. Check Balance");
            System.out.println("7. Remove Account");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1 -> register();
                case 2 -> deposit();
                case 3 -> withdraw();
                case 4 -> intraTransfer();
                case 5 -> interTransfer();
                case 6 -> checkBalance();
                case 7 -> removeAccount();
                case 8 -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private static Bank pickBank() {
        System.out.print("Bank name: ");
        String name = input.nextLine().trim();
        Bank bank = banks.findBank(name);
        if (bank == null) throw new IllegalArgumentException("Bank not found: " + name);
        return bank;
    }

    private static void register() {
        try {
            Bank bank = pickBank();
            System.out.print("First name: ");
            String first = input.nextLine().trim();
            System.out.print("Last name: ");
            String last = input.nextLine().trim();
            System.out.print("Enter your 4-digit PIN: ");
            String pin = input.nextLine().trim();
            Account account = bank.registerCustomer(first, last, pin);
            System.out.println("Success! Account number is: " + account.getNumber());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void deposit() {
        try {
            Bank bank = pickBank();
            System.out.print("Account number: ");
            String number = input.nextLine().trim();
            System.out.print("Amount: ");
            double amount = input.nextDouble();
            input.nextLine();
            bank.deposit(number, amount);
            System.out.println("Deposit successful.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void withdraw() {
        try {
            Bank bank = pickBank();
            System.out.print("Account number: ");
            String number = input.nextLine().trim();
            System.out.print("Amount: ");
            double amount = input.nextDouble();
            input.nextLine();

            System.out.print("PIN: ");
            String pin = input.nextLine().trim();
            bank.withdraw(number, amount, pin);
            System.out.println("Withdrawal successful.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void intraTransfer() {
        try {
            Bank bank = pickBank();
            System.out.print("Your account number: ");
            String sender = input.nextLine().trim();
            System.out.print("Recipient account number: ");
            String receiver = input.nextLine().trim();
            System.out.print("Amount: ");
            double amount = input.nextDouble();
            input.nextLine();

            System.out.print("PIN: ");
            String pin = input.nextLine().trim();

            bank.transfer(sender, receiver, amount, pin);
            System.out.println("Transfer successful.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void interTransfer() {
        try {
            System.out.print("Your bank name: ");
            String senderBank = input.nextLine().trim();
            System.out.print("Your account number: ");
            String senderAccount = input.nextLine().trim();
            System.out.print("Recipient bank name: ");
            String receiverBank = input.nextLine().trim();
            System.out.print("Recipient account number: ");
            String receiverAccount = input.nextLine().trim();
            System.out.print("Amount: ");
            double amount = input.nextDouble();
            input.nextLine();

            System.out.print("Your PIN: ");
            String pin = input.nextLine().trim();

            banks.interBankTransfer(senderBank, senderAccount, receiverBank, receiverAccount, amount, pin);
            System.out.println("Inter-bank transfer successful.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void checkBalance() {
        try {
            Bank bank = pickBank();
            System.out.print("Account number: ");
            String number = input.nextLine().trim();
            System.out.print("PIN: ");
            String pin = input.nextLine().trim();
            System.out.println("Balance: " + bank.checkBalance(number, pin));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void removeAccount() {
        try {
            Bank bank = pickBank();
            System.out.print("Account number: ");
            String number = input.nextLine().trim();
            System.out.print("PIN: ");
            String pin = input.nextLine().trim();
            bank.removeAccount(number, pin);
            System.out.println("Account removed.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
