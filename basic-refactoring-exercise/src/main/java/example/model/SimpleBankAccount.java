package example.model;

/**
 * This class represent a particular instance of a BankAccount.
 * In particular, a Simple Bank Account allows always the deposit
 * while the withdrawal is allowed only if the balance greater or equal the withdrawal amount
 */
public class SimpleBankAccount implements BankAccount {

    private double balance;
    private final AccountHolder holder;

    public SimpleBankAccount(final AccountHolder holder, final double balance) {
        this.holder = holder;
        this.balance = balance;
    }

    @Override
    public double getBalance() {
        return this.balance;
    }

    @Override
    public void deposit(final int userID, final double amount) {
        if (isUserOwnerOfThisAccount(userID)) {
            this.balance += amount;
        }
    }

    @Override
    public void withdraw(final int userID, final double amount) {
        final boolean canWithdraw = isUserOwnerOfThisAccount(userID) && isWithdrawAllowed(amount);
        if (canWithdraw) {
            this.balance -= amount;
        }
    }

    protected boolean isWithdrawAllowed(final double amount) {
        return this.balance >= amount && amount > getMinimalWithdrawAmount();
    }

    protected double getMinimalWithdrawAmount(){
        return 0;
    }

    private boolean isUserOwnerOfThisAccount(final int id) {
        return this.holder.id() == id;
    }
}
