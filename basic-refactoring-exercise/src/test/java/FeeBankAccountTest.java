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
        final int amountToWithdraw = 70;
        final int expectedRemainingAmount =
                DEFAULT_AMOUNT_TO_DEPOSIT - amountToWithdraw - FeedBankAccount.WITHDRAW_FEE;
        assertBalanceIsEqualToExpectedAmountAfterDefaultDepositAndWithdraw(
                expectedRemainingAmount,
                amountToWithdraw
        );
    }
}
