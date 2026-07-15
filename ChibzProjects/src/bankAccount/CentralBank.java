package bankAccount;

import java.util.ArrayList;

public class CentralBank {
    private ArrayList<Bank> banks = new ArrayList<>();

    public void addBank(Bank bank) {
        if (findBank(bank.getName()) != null) {
            throw new IllegalArgumentException("A bank with that name already exists in the system");
        }
        banks.add(bank);
    }

    public void removeBank(String name) {
        Bank bank = findBank(name);
        if (bank == null) throw new IllegalArgumentException("Bank not found inside the system");
        banks.remove(bank);
    }

    public Bank findBank(String name) {
        for (Bank bank : banks) {
            if (bank.getName().equals(name)) {
                return bank;
            }
        }
        return null;
    }

    public void interBankTransfer(String myBankName, String myAccountNumber, String myGuyBankName, String myGuyAccountNumber, double amount, String pin) {
        Bank senderBank = findBank(myBankName);
        Bank receiverBank = findBank(myGuyBankName);

        if (senderBank == null) throw new IllegalArgumentException("Sender bank not found");
        if (receiverBank == null) throw new IllegalArgumentException("Receiver bank not found");

        senderBank.withdraw(myAccountNumber, amount, pin);
        receiverBank.deposit(myGuyAccountNumber, amount);
    }

    public ArrayList<Bank> getBanks() {
        return banks;
    }
}
