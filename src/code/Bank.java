import java.util.ArrayList;
import java.util.List;

public class Bank {

    private final List<BankAccount> accounts = new ArrayList<BankAccount>();

    void addAccount(BankAccount account1) {
        accounts.add(account1);
    }

    public BankAccount retrieveAccount(String number) {
        BankAccount.validateBankID(number);
        for (BankAccount account : accounts) {
            if(account.getAccountNumber().equals(number)) {
                return account;
            }
        }
        throw new IllegalArgumentException("Account not found");
    }

    public int totalBalanceUsd() {
        return accounts.stream().mapToInt(BankAccount::getBalance).sum();
    }
}
