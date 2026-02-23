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
        final int ACCOUNT_ID = 1;
        final String USER_NAME = "Mario";
        final String USER_SURNAME = "Rossi";

        accountHolder = new AccountHolder(USER_NAME, USER_SURNAME, ACCOUNT_ID);
        bankAccount = new SimpleBankAccount(accountHolder, ACCOUNT_INITIAL_BALANCE);
    }

    protected void doDefaultDeposit() {
        bankAccount.deposit(accountHolder.id(), DEFAULT_AMOUNT_TO_DEPOSIT);
    }

    protected void doWithdrawInCorrectAccount(final double amountToWithdraw) {
        bankAccount.withdraw(accountHolder.id(), amountToWithdraw);
    }

    protected void assertBalanceIsEqualToExpectedAmountAfterDefaultDepositAndWithdraw(double expectedAmount, double toWithdraw) {
        doDefaultDeposit();
        doWithdrawInCorrectAccount(toWithdraw);
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
        final int AMOUNT_TO_DEPOSIT_IN_WRONG_ACCOUNT = 50;
        doDefaultDeposit();
        bankAccount.deposit(WRONG_ACCOUNT_ID, AMOUNT_TO_DEPOSIT_IN_WRONG_ACCOUNT);
        assertEquals(DEFAULT_AMOUNT_TO_DEPOSIT, bankAccount.getBalance());
    }

    @Test
    void testWithdraw() {
        final int AMOUNT_TO_WITHDRAW = 70;
        final int EXPECTED_REMAINING_AMOUNT = DEFAULT_AMOUNT_TO_DEPOSIT - AMOUNT_TO_WITHDRAW;
        assertBalanceIsEqualToExpectedAmountAfterDefaultDepositAndWithdraw(
                EXPECTED_REMAINING_AMOUNT,
                AMOUNT_TO_WITHDRAW
        );
    }

    @Test
    void testWrongWithdraw() {
        final int AMOUNT_TO_WITHDRAW_IN_WRONG_ACCOUNT = 70;
        doDefaultDeposit();
        bankAccount.withdraw(WRONG_ACCOUNT_ID, AMOUNT_TO_WITHDRAW_IN_WRONG_ACCOUNT);
        assertEquals(DEFAULT_AMOUNT_TO_DEPOSIT, bankAccount.getBalance());
    }

    @Test
    void testNegativeAmountWithdraw() {
        final int AMOUNT_TO_WITHDRAW = -100;
        assertBalanceIsEqualToExpectedAmountAfterDefaultDepositAndWithdraw(
                DEFAULT_AMOUNT_TO_DEPOSIT,
                AMOUNT_TO_WITHDRAW
        );
    }

    @Test
    void testZeroAmountToWithdraw() {
        final int AMOUNT_TO_WITHDRAW = 0;
        assertBalanceIsEqualToExpectedAmountAfterDefaultDepositAndWithdraw(
                DEFAULT_AMOUNT_TO_DEPOSIT,
                AMOUNT_TO_WITHDRAW
        );
    }
}
