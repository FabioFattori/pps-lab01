import example.model.AccountHolder;
import example.model.BankAccount;
import example.model.SimpleBankAccount;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the SimpleBankAccount implementation
 */
class SimpleBankAccountTest {

    private static final int DEFAULT_AMOUNT_TO_DEPOSIT = 100;
    private static final double ACCOUNT_INITIAL_BALANCE = 0;
    private static final int WRONG_ACCOUNT_ID = 2;
    private AccountHolder accountHolder;
    private BankAccount bankAccount;

    @BeforeEach
    void beforeEach() {
        final int ACCOUNT_ID = 1;
        final String USER_NAME = "Mario";
        final String USER_SURNAME = "Rossi";

        accountHolder = new AccountHolder(USER_NAME, USER_SURNAME, ACCOUNT_ID);
        bankAccount = new SimpleBankAccount(accountHolder, ACCOUNT_INITIAL_BALANCE);
    }

    private void doDefaultDeposit() {
        bankAccount.deposit(accountHolder.id(), DEFAULT_AMOUNT_TO_DEPOSIT);
    }

    private void doWithdrawInCorrectAccount(final double amountToWithdraw){
        bankAccount.withdraw(accountHolder.id(), amountToWithdraw);
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
        final int EXPECTED_REMAINING_AMOUNT = DEFAULT_AMOUNT_TO_DEPOSIT - AMOUNT_TO_WITHDRAW - SimpleBankAccount.WITHDRAW_FEE;
        doDefaultDeposit();
        doWithdrawInCorrectAccount(AMOUNT_TO_WITHDRAW);
        assertEquals(EXPECTED_REMAINING_AMOUNT, bankAccount.getBalance());
    }

    @Test
    void testWrongWithdraw() {
        final int AMOUNT_TO_WITHDRAW_IN_WRONG_ACCOUNT = 70;
        doDefaultDeposit();
        bankAccount.withdraw(WRONG_ACCOUNT_ID, AMOUNT_TO_WITHDRAW_IN_WRONG_ACCOUNT);
        assertEquals(DEFAULT_AMOUNT_TO_DEPOSIT, bankAccount.getBalance());
    }

    @Test
    void testNegativeAmountWithdraw(){
        final int AMOUNT_TO_WITHDRAW = -100;
        doDefaultDeposit();
        doWithdrawInCorrectAccount(AMOUNT_TO_WITHDRAW);
        assertEquals(DEFAULT_AMOUNT_TO_DEPOSIT,bankAccount.getBalance());
    }

    @Test
    void testZeroAmountToWithdraw(){
        final int AMOUNT_TO_WITHDRAW = 0;
        doDefaultDeposit();
        doWithdrawInCorrectAccount(AMOUNT_TO_WITHDRAW);
        assertEquals(DEFAULT_AMOUNT_TO_DEPOSIT,bankAccount.getBalance());
    }
}
