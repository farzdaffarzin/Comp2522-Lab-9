public class BankAccount {

    private int balance;
    private final String accountNumber;

    public String getAccountNumber() {
        return accountNumber;
    }

    public int getBalance() {
        return balance;
    }

    public BankAccount(String number, int balance) {
        validateBankID(number);

        this.accountNumber = number;
        this.balance = balance;
    }

    public void deposit(int amount) {
        balance += amount;
    }

    public int getBalanceUsd() {
        return balance;
    }

    public void withdraw(int amount) {
        if(amount > balance) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        balance -= amount;
    }

    public void transferToBank(BankAccount account, String number, int amount) {
        if(account.getAccountNumber().equals(number)) {
            withdraw(amount);
        };

    }

    public static void validateBankID(String bankID) {
        if (bankID.length() != 5) {
            throw new IllegalArgumentException("Bank ID must have exactly 5 characters");
        }
    }
}
