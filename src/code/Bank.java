import java.util.ArrayList;
import java.util.List;

public class Bank {

    private final List<BankAccount> accounts;

    public Bank(){
        accounts = new ArrayList<>();
    }

    void addAccount(final BankAccount account1) {
        //check if any account already exists with the account number
        for(BankAccount account : accounts){
            if(account.getAccountNumber().equals(account1.getAccountNumber())){
                throw new IllegalArgumentException("Account already exists");
            }
        }
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
        return accounts.stream()
                .mapToInt(BankAccount::getBalance)
                .sum();
    }

    @Override
    public String toString() {
        return "Bank{" + "accounts=" + accounts + '}';
    }
}
