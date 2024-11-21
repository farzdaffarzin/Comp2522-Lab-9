public class BankAccount {

    private int balance;
    private final String accountNumber;

    public String getAccountNumber() {
        return accountNumber;
    }

    public int getBalance() {
        return balance;
    }

    public BankAccount(final String number, int balance) {
        validateBankID(number);

        if(balance < 0){
            throw new IllegalArgumentException("Balance cannot be negative");
        }

        this.accountNumber = number;
        this.balance = balance;
    }

    public void deposit(final int amount) {
        if(amount < 0){
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        balance += amount;
    }

    public int getBalanceUsd() {
        return balance;
    }

    public void withdraw(final int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        balance -= amount;
    }

    public void transferToBank(final BankAccount account,
                               final String number,
                               final int amount) {

        if(!account.getAccountNumber().equals(number)){
            throw new IllegalArgumentException("Account number does not match");
        }
        withdraw(amount);
        account.deposit(amount);

    }

    public static void validateBankID(final String bankID) {
        if (bankID.length() != 5) {
            throw new IllegalArgumentException("Bank ID must have exactly 5 characters");
        }
    }

    @Override
    public String toString(){
        String bankInfo = "BankAccount [accountNumber=" +
                accountNumber + ", balance=" + balance + "]";;
        return bankInfo;
    }
}
