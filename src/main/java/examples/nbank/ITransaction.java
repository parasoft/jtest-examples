package examples.nbank;

/**
 * Base interface for all transactions.
 * 
 * @author <b>John</b>, Copyright &#169; 1998"
 */
public interface ITransaction {

    /**
     * Transaction fee, must be greater or equal to zero.
     * 
     * @post $result >= 0
     */
    int fee();

    /**
     * Performs the transaction.
     * 
     * @param account
     * @return true if the transaction succeeds, false otherwise
     * @pre account != null
     */
    boolean apply(Account account);
}
