package examples.nbank;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a Bank (collection of accounts).
 * 
 * @author Elizabeth
 */
public class Bank {

    private Map<String, Account> _accounts = new HashMap<String, Account>();

    private static LogAccountInfo _logger = new LogAccountInfo();

    private static Integer ACCOUNTS_LIMIT = 10;

    public Bank() {
        initialize();
    }

    private void initialize() {
        Customer smith3453 = new Customer("Jim Smith", "000-00-0000");
        this.addAccount(new Account(smith3453, 1000));
        Customer miller974 = new Customer("Marc Miller", "111-11-1111");
        this.addAccount(new Account(miller974, 200));
        Customer johnson265 = new Customer("John Johnson", "222-22-2222");
        this.addAccount(new Account(johnson265, 5000));
    }

    public Boolean addAccount(Account account) {
        if (_accounts.size() >= ACCOUNTS_LIMIT) {
            return false;
        }
        _logger.log(account);
        _accounts.put(account.getID(), account);
        return true;
    }

    public void closeAccounts(List<Account> list) {
        int size = (list != null) ? list.size() : 0;
        for (int i = 0; i < size; i++) {
            Account account = list.get(i);
            _logger.log(account);
            _accounts.remove(account.getID());
            i = size;
        }
    }

    public Account getAccount(String id, String name) {
        Account userAccount = null;
        if (_accounts.size() > 0) {
            userAccount = (Account) _accounts.get(id);
        }
        if ((userAccount != null) && !name.equals(userAccount.getCustomer().getName())) {
            // account wrong if account number does not match
            userAccount = null;
        }
        if (userAccount != null) {
             _logger.log(userAccount);
        }
        return userAccount;
    }

    public Boolean isMaintenanceMode() {
        return BankState.isMaintenanceMode();
    }

    public void startMaintenance() {
        BankState.startMaintenance();
    }

    public void endMaintenance() {
        BankState.endMaintenance();
    }

    public static void setAccountsLimit(Integer limit) {
        ACCOUNTS_LIMIT = limit;
    }
}
