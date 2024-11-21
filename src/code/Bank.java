import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Bank that manages multiple BankAccounts.
 *
 * @author Dalraj Bains
 * @author Anil Bronson
 * @author Farzad Farzin
 * @author Arsh Mann
 * @version 1.0
 */
public class Bank {

    private final List<BankAccount> accounts;

    /**
     * Creates a new Bank instance.
     */
    public Bank(){
        accounts = new ArrayList<>();
    }

    /**
     * Adds a new bank account to the list of accounts.
     *
     * @param account The bank account to add.
     * @throws IllegalArgumentException if an account with the same account number already exists.
     */
    public void addAccount(final BankAccount account) {
        for (final BankAccount acc : accounts) {
            if (acc.getAccountNumber().equals(account.getAccountNumber())) {
                throw new IllegalArgumentException("Account with this number already exists");
            }
        }
        accounts.add(account);
    }

    /**
     * Retrieves a bank account by its account number.
     *
     * @param accountNumber The account number of the bank account to retrieve.
     * @return The bank account associated with the provided account number.
     * @throws IllegalArgumentException if the account is not found or the account number is invalid.
     */
    public BankAccount retrieveAccount(final String accountNumber) {

        BankAccount.validateBankID(accountNumber);

        for (final BankAccount account : accounts) {
            if(account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        throw new IllegalArgumentException("Account not found");
    }

    /**
     * Calculates the total balance of all accounts in USD.
     *
     * @return The total balance in USD.
     */
    public int totalBalanceUsd() {
        final int output;
        output = accounts.stream()
                .mapToInt(BankAccount::getBalanceUSD)
                .sum();
        return output;
    }

    /**
     * Returns a string representation of the Bank instance.
     *
     * @return A string representation of the Bank.
     */
    @Override
    public String toString() {
        final String output;
        output ="Bank{" +
                "accounts=" +
                accounts +
                '}';
        return output;
    }
}
