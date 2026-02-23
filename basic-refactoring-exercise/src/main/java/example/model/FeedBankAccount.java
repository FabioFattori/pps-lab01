package example.model;

public class FeedBankAccount extends SimpleBankAccount {
    public static final int WITHDRAW_FEE = 1;

    public FeedBankAccount(AccountHolder holder, double balance) {
        super(holder, balance);
    }

    @Override
    public void withdraw(final int userID, final double amount) {
        super.withdraw(userID, amount + WITHDRAW_FEE);
    }

    @Override
    protected double getMinimalWithdrawAmount() {
        return 1;
    }

    @Override
    protected boolean isWithdrawAllowed(final double amount){
        return super.isWithdrawAllowed(amount);
    }
}
