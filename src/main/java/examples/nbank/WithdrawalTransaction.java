package examples.nbank;

/**
 * Withdrawal transaction.
 * 
 * @author John
 */
public class WithdrawalTransaction extends AbstractTransaction {

    private static final int MAX_WITHDRAW = 1000;

    public WithdrawalTransaction(int amount) {
        if ((amount < 0) && (amount > MAX_WITHDRAW))
            throw new IllegalArgumentException("Invalid withdrawal amount: " + amount);
        _amount = amount;
    }

    public boolean apply(Account account) {
        account.setBalance(account.getBalance() - _amount);
        return true;
    }

    private int _amount;
}
