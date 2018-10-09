package examples.nbank;

/**
 * Base abstract class for transactions
 * 
 * @author John 23/11/2004
 */
public abstract class AbstractTransaction implements ITransaction {

    public AbstractTransaction() {
        if (!isTransactionAvailable())
            throw new IllegalStateException("Bank is under maintenance");
    }

    public int fee() {
        return 0;
    }

    private Boolean isTransactionAvailable() {
        return !BankState.isMaintenanceMode();
    }
}
