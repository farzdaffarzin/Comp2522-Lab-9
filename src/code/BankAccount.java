/**
 * Represents a bank account with basic operations such as deposit, withdrawal,
 * and transfer to another bank account.
 *
 * @author Dalraj Bains
 * @author Anil Bronson
 * @author Farzad Farzin
 * @author Arsh Mann
 * @version 1.0
 */
public class BankAccount {

    private static final int BANK_ACC_LENGTH = 5;
    private static final int MIN_BALANCE_USD = 0;

    private int balanceUSD;
    private final String accountNumber;

    /**
     * Returns the account number of this bank account.
     *
     * @return The account number.
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Returns the current balance of this bank account.
     *
     * @return The balance.
     */
    public int getBalanceUSD() {
        return balanceUSD;
    }

    /**
     * Creates a new BankAccount with the specified account number and initial balance.
     *
     * @param number The account number.
     * @param balanceUSD The initial balance.
     * @throws IllegalArgumentException if the account number is invalid or the balance less than MIN_BALANCE_USD.
     */
    public BankAccount(final String number, final int balanceUSD) {
        validateBankID(number);

        if(balanceUSD < MIN_BALANCE_USD){
            throw new IllegalArgumentException("Balance cannot be under " + MIN_BALANCE_USD);
        }

        this.accountNumber = number;
        this.balanceUSD = balanceUSD;
    }

    /**
     * Deposits the specified amount into this bank account.
     *
     * @param amountUSD The amount to deposit.
     * @throws IllegalArgumentException if the deposit amount invalid.
     */
    public void deposit(final int amountUSD) {
        if(amountUSD <= MIN_BALANCE_USD){
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        balanceUSD += amountUSD;
    }

    /**
     * Returns the balance of this bank account in USD.
     *
     * @return The balance in USD.
     */
    public int getBalanceUsd() {
        return balanceUSD;
    }

    /**
     * Withdraws the specified amount from this bank account.
     *
     * @param amountUSD The amount to withdraw.
     * @throws IllegalArgumentException if the withdrawal amount is not positive or exceeds the current balance.
     */
    public void withdraw(final int amountUSD)
    {
        if (amountUSD <= MIN_BALANCE_USD)
        {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }

        if (amountUSD > balanceUSD) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        balanceUSD -= amountUSD;
    }

    /**
     * Transfers the specified amount from this bank account to another bank account.
     *
     * @param recipient The recipient bank account.
     * @param recipientAccountNumber The account number of the recipient bank account.
     * @param amountUSD The amount to transfer.
     * @throws IllegalArgumentException if the recipient's account number does not match or if the transfer amount is invalid.
     */
    public void transferToBank(final BankAccount recipient,
                               final String recipientAccountNumber,
                               final int amountUSD) {
        if (!recipient.getAccountNumber().equals(recipientAccountNumber)) {
            throw new IllegalArgumentException("Recipient account number mismatch");
        }
        this.withdraw(amountUSD);
        recipient.deposit(amountUSD);
    }

    /**
     * Validates the bank account ID.
     *
     * @param bankID The bank account ID to validate.
     * @throws IllegalArgumentException if the bank ID does not have exactly 5 characters.
     */
    public static void validateBankID(final String bankID) {
        if (bankID.length() != BANK_ACC_LENGTH) {
            throw new IllegalArgumentException("Bank ID must have exactly 5 characters");
        }
    }

    /**
     * Returns a string representation of the bank account.
     *
     * @return A string representation of the bank account.
     */
    @Override
    public String toString(){
        final String bankInfo;

        bankInfo = "BankAccount [accountNumber=" +
                accountNumber +
                ", balance=" +
                balanceUSD +
                "]";;
        return bankInfo;
    }
}
