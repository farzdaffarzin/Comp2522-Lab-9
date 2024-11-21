import java.util.ArrayList;
import java.util.List;

public class Bank {

    private final List<BankAccount> accounts;

    public Bank(){
        accounts = new ArrayList<>();
    }

    public void addAccount(BankAccount account) {
        for (BankAccount acc : accounts) {
            if (acc.getAccountNumber().equals(account.getAccountNumber())) {
                throw new IllegalArgumentException("Account with this number already exists");
            }
        }
        accounts.add(account);
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
        return accounts.stream()
                .mapToInt(BankAccount::getBalance)
                .sum();
    }

    @Override
    public String toString() {
        return "Bank{" + "accounts=" + accounts + '}';
    }
}
