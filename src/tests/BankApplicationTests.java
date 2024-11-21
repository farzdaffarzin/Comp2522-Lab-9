import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for Bank Application.
 *
 * @author Dalraj Bains
 * @author Anil Bronson
 * @author Farzad Farzin
 * @author Arsh Mann
 * @version 1.0
 */
public class BankApplicationTests {
    private Bank bank1;
    private Bank bank2;
    private BankAccount account1;
    private BankAccount account2;

    @BeforeEach
    void setUp() {
        bank1 = new Bank();
        bank2 = new Bank();
        account1 = new BankAccount("12345", 1000);
        account2 = new BankAccount("67890", 500);
        bank1.addAccount(account1);
        bank2.addAccount(account2);
    }

    @Test
    void depositIncreasesBalanceAndVerify() {
        account1.deposit(200);
        assertEquals(1200, account1.getBalanceUsd());
        account2.deposit(300);
        assertEquals(800, account2.getBalanceUsd());
    }

    @Test
    void withdrawDecreasesBalanceAndVerify() {
        account1.withdraw(200);
        assertEquals(800, account1.getBalanceUsd());
        account2.withdraw(100);
        assertEquals(400, account2.getBalanceUsd());
    }

    @Test
    void cannotWithdrawMoreThanBalanceAndHandleException() {
        IllegalArgumentException exception1 =
                assertThrows(IllegalArgumentException.class, () -> account1.withdraw(1200));
        assertEquals("Insufficient funds", exception1.getMessage());
        IllegalArgumentException exception2 =
                assertThrows(IllegalArgumentException.class, () -> account2.withdraw(600));
        assertEquals("Insufficient funds", exception2.getMessage());
    }

    @Test
    void addingAndRetrievingAccountFromBank() {
        final BankAccount newAccount;
        newAccount = new BankAccount("54321", 100);
        bank2.addAccount(newAccount);
        assertEquals(newAccount, bank2.retrieveAccount("54321"));
        BankAccount newAccount2 = new BankAccount("11111", 300);
        bank1.addAccount(newAccount2);
        assertEquals(newAccount2, bank1.retrieveAccount("11111"));
    }

    @Test
    void transferBetweenBankAccountsAndVerifyBalances() {
        account1.transferToBank(account2, "67890", 200);
        assertEquals(800, account1.getBalanceUsd());
        assertEquals(700, account2.getBalanceUsd());
        account2.transferToBank(account1, "12345", 100);
        assertEquals(900, account1.getBalanceUsd());
        assertEquals(600, account2.getBalanceUsd());
    }

    @Test
    void totalBalanceCalculationForBanks() {
        assertEquals(1000, bank1.totalBalanceUsd());
        assertEquals(500, bank2.totalBalanceUsd());
        bank1.addAccount(new BankAccount("33333", 200));
        assertEquals(1200, bank1.totalBalanceUsd());
    }

    @Test
    void handlingInvalidAccountRetrieval() {
        IllegalArgumentException exception1 =
                assertThrows(IllegalArgumentException.class, () ->
                        bank1.retrieveAccount("99999"));
        assertEquals("Account not found", exception1.getMessage());
        IllegalArgumentException exception2 =
                assertThrows(IllegalArgumentException.class, () ->
                        bank2.retrieveAccount("00000"));
        assertEquals("Account not found", exception2.getMessage());
    }

    // Additional Tests
    @Test
    void cannotDepositZeroOrNegativeAmounts() {
        IllegalArgumentException exception1 =
                assertThrows(IllegalArgumentException.class, () -> account1.deposit(0));
        assertEquals("Deposit amount must be positive", exception1.getMessage());

        IllegalArgumentException exception2 =
                assertThrows(IllegalArgumentException.class, () -> account1.deposit(-100));
        assertEquals("Deposit amount must be positive", exception2.getMessage());
    }

    @Test
    void cannotWithdrawZeroOrNegativeAmounts() {
        IllegalArgumentException exception1 =
                assertThrows(IllegalArgumentException.class, () -> account1.withdraw(0));
        assertEquals("Withdrawal amount must be positive", exception1.getMessage());

        IllegalArgumentException exception2 =
                assertThrows(IllegalArgumentException.class, () -> account1.withdraw(-100));
        assertEquals("Withdrawal amount must be positive", exception2.getMessage());
    }

    @Test
    void cannotAddDuplicateAccountsToBank() {
        BankAccount duplicateAccount = new BankAccount("12345", 200);
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> bank1.addAccount(duplicateAccount));
        assertEquals("Account with this number already exists", exception.getMessage());
    }

    @Test
    void transferBetweenAccountsWithInvalidRecipientDetails() {
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> account1.transferToBank(account2, "11111", 200));
        assertEquals("Recipient account number mismatch", exception.getMessage());
    }

    @Test
    void totalBalanceAfterMultipleTransactions() {
        account1.deposit(500);
        account2.withdraw(100);
        assertEquals(1500, account1.getBalanceUsd());
        assertEquals(400, account2.getBalanceUsd());
        assertEquals(1900, bank1.totalBalanceUsd() + bank2.totalBalanceUsd());
    }

    @Test
    void invalidBankIDFormatThrowsException() {
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> new BankAccount("1234", 500));
        assertEquals("Bank ID must have exactly 5 characters", exception.getMessage());
    }

    @Test
    void totalBalanceCalculationAfterTransfers() {
        account1.transferToBank(account2, "67890", 200);
        assertEquals(800, account1.getBalanceUsd());
        assertEquals(700, account2.getBalanceUsd());
        assertEquals(1500, bank1.totalBalanceUsd() + bank2.totalBalanceUsd());
    }
}
