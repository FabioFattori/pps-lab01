import example.model.AccountHolder;
import example.model.BankAccount;
import example.model.SimpleBankAccount;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the SimpleBankAccount implementation
 */
class SimpleBankAccountTest {

    protected static final int DEFAULT_AMOUNT_TO_DEPOSIT = 100;
    protected static final double ACCOUNT_INITIAL_BALANCE = 0;
    protected static final int WRONG_ACCOUNT_ID = 2;
    protected AccountHolder accountHolder;
    protected BankAccount bankAccount;

    @BeforeEach
    void beforeEach() {
        accountHolder = new AccountHolder("Mario", "Rossi", 1);
        bankAccount = new SimpleBankAccount(accountHolder, ACCOUNT_INITIAL_BALANCE);
    }

    protected void doDefaultDeposit() {
        bankAccount.deposit(accountHolder.id(), DEFAULT_AMOUNT_TO_DEPOSIT);
    }

    protected void assertBalanceIsEqualToExpectedAmountAfterDefaultDepositAndWithdraw(double expectedAmount, double toWithdraw) {
        doDefaultDeposit();
        bankAccount.withdraw(accountHolder.id(), toWithdraw);
        assertEquals(expectedAmount, bankAccount.getBalance());
    }

    @Test
    void testInitialBalance() {
        assertEquals(ACCOUNT_INITIAL_BALANCE, bankAccount.getBalance());
    }

    @Test
    void testDeposit() {
        doDefaultDeposit();
        assertEquals(DEFAULT_AMOUNT_TO_DEPOSIT, bankAccount.getBalance());
    }

    @Test
    void testWrongDeposit() {
        final int amountToDepositInWrongAccount = 50;
        doDefaultDeposit();
        bankAccount.deposit(WRONG_ACCOUNT_ID, amountToDepositInWrongAccount);
        assertEquals(DEFAULT_AMOUNT_TO_DEPOSIT, bankAccount.getBalance());
    }

    @Test
    void testWithdraw() {
        final int amountToWithdraw = 70;
        final int expectedRemainingAmount = DEFAULT_AMOUNT_TO_DEPOSIT - amountToWithdraw;
        assertBalanceIsEqualToExpectedAmountAfterDefaultDepositAndWithdraw(
                expectedRemainingAmount,
                amountToWithdraw
        );
    }

    @Test
    void testWrongWithdraw() {
        final int amountToWithdrawInWrongAccount = 70;
        doDefaultDeposit();
        bankAccount.withdraw(WRONG_ACCOUNT_ID, amountToWithdrawInWrongAccount);
        assertEquals(DEFAULT_AMOUNT_TO_DEPOSIT, bankAccount.getBalance());
    }

    @Test
    void testNegativeAmountWithdraw() {
        final int amountToWithdraw = -100;
        assertBalanceIsEqualToExpectedAmountAfterDefaultDepositAndWithdraw(
                DEFAULT_AMOUNT_TO_DEPOSIT,
                amountToWithdraw
        );
    }

    @Test
    void testZeroAmountToWithdraw() {
        final int amountToWithdraw = 0;
        assertBalanceIsEqualToExpectedAmountAfterDefaultDepositAndWithdraw(
                DEFAULT_AMOUNT_TO_DEPOSIT,
                amountToWithdraw
        );
    }
}
