import example.model.FeedBankAccount;
import org.junit.jupiter.api.BeforeEach;

public class FeeBankAccountTest extends SimpleBankAccountTest {
    @Override
    @BeforeEach
    void beforeEach() {
        super.beforeEach();
        bankAccount = new FeedBankAccount(accountHolder, ACCOUNT_INITIAL_BALANCE);
    }

    @Override
    void testWithdraw() {
        final int AMOUNT_TO_WITHDRAW = 70;
        final int EXPECTED_REMAINING_AMOUNT = DEFAULT_AMOUNT_TO_DEPOSIT - AMOUNT_TO_WITHDRAW - FeedBankAccount.WITHDRAW_FEE;
        assertBalanceIsEqualToExpectedAmountAfterDefaultDepositAndWithdraw(EXPECTED_REMAINING_AMOUNT, AMOUNT_TO_WITHDRAW);
    }
}
