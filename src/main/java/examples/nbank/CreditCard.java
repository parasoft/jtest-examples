package examples.nbank;

/**
 * Credit card account (identical to CreditCard). This class gets about 98% line
 * coverage when run in Jtest with Generation enabled, covering all methods in
 * the class. Jtest automatically uses the CreditCard sample object defined by
 * user to generate a valid credit card object.
 * 
 * @see CreditCard
 */
public class CreditCard {

    public static final int[] SSN_DIGITS = new int[] { 3, 2, 4 };

    public static final int[] ZIP_DIGITS = new int[] { 5 };

    public static final int[] CARD_DIGITS = new int[] { 4, 4, 4, 4 };

    private int _currentBalance;

    private String _customerId;

    private String _creditCardNumber;

    private String _socialSecurityNumber;

    private String _zipcode;

    private String _customerName;

    /**
     * Constructor. Only accepts valid inputs.
     * 
     * @pre securityNumber != null
     * @pre zipcode != null
     * @pre creditCardNumber != null
     * @throws IllegalArgumentException
     *             invalid credit card data
     */
    public CreditCard(int balance, String id, String securityNumber, String name, String zipcode,
            String creditCardNumber) throws IllegalArgumentException {
        super();
        _currentBalance = balance;
        _customerId = id;
        _socialSecurityNumber = securityNumber;
        _customerName = name;
        _zipcode = zipcode;
        _creditCardNumber = creditCardNumber;
        if (!validate())
            throw new IllegalArgumentException("Invalid credit card data");
    }

    /**
     * Validate the credit card data, including zipcode, social security number,
     * credit card numbers, balance, customer name and id.
     */
    private boolean validate() {
        return validate(ZIP_DIGITS, _zipcode) && validate(SSN_DIGITS, _socialSecurityNumber)
                && validate(CARD_DIGITS, _creditCardNumber) && (_currentBalance > 0) && (_customerId.length() != 0)
                && (_customerName.length() != 0);
    }

    /**
     * Validates the input string using an array of digit sequence lengths,
     * separated by dashes. E.g. "123-45-6789" is a sequence of 3 digits, followed
     * by dash, followed by sequence of 2 digits, dash, and a sequence of 4 digits.
     */
    public static boolean validate(int[] digLengths, String input) {
        // index into the input string
        int index = 0;
        for (int i = 0; i < digLengths.length; i++) {
            int length = digLengths[i];
            // expect the dash between digit sequences
            if ((i != 0) && ((index == input.length()) || (input.charAt(index++) != '-')))
                return false;
            // expect a sequence of digits of given length
            for (int j = 0; j < length; j++) {
                if (index == input.length() || !Character.isDigit(input.charAt(index++)))
                    return false;
            }
        }
        return index == input.length();
    }

    /**
     * Displays a credit card statement.
     */
    public void displayStatement() {
        System.out.println("Customer Name: " + _customerName);
        System.out.println("Account Number: " + _customerId);
        System.out.println("Credit Card Number: xxxx-xxxx-xxxx-" + _creditCardNumber.substring(15));
        System.out.println("Balance: $" + _currentBalance + ".00");
    }

    /**
     * Makes a credit card payment from the customer's bank account.
     * 
     * @pre account != null
     * @throws IllegalArgumentException
     *             bank account customer id does not match the credit card customer
     *             id
     */
    public void makePayment(Account account) throws IllegalArgumentException {
        if (account.getID() != _customerId)
            throw new IllegalArgumentException("Wrong customer id");
        account.apply(new DepositTransaction(_currentBalance));
        _currentBalance = 0;
    }

    /**
     * @return Returns the _creditCardNumber.
     */
    public String get_creditCardNumber() {
        return _creditCardNumber;
    }

    /**
     * @param cardNumber
     *            The _creditCardNumber to set.
     */
    public void set_creditCardNumber(String cardNumber) {
        _creditCardNumber = cardNumber;
    }

    /**
     * @return Returns the _currentBalance.
     */
    public int get_currentBalance() {
        return _currentBalance;
    }

    /**
     * @param balance
     *            The _currentBalance to set.
     */
    public void set_currentBalance(int balance) {
        _currentBalance = balance;
    }

    /**
     * @return Returns the _customerId.
     */
    public String get_customerId() {
        return _customerId;
    }

    /**
     * @param id
     *            The _customerId to set.
     */
    public void set_customerId(String id) {
        _customerId = id;
    }

    /**
     * @return Returns the _customerName.
     */
    public String get_customerName() {
        return _customerName;
    }

    /**
     * @param name
     *            The _customerName to set.
     */
    public void set_customerName(String name) {
        _customerName = name;
    }

    /**
     * @return Returns the _socialSecurityNumber.
     */
    public String get_socialSecurityNumber() {
        return _socialSecurityNumber;
    }

    /**
     * @param securityNumber
     *            The _socialSecurityNumber to set.
     */
    public void set_socialSecurityNumber(String securityNumber) {
        _socialSecurityNumber = securityNumber;
    }

    /**
     * @return Returns the _zipcode.
     */
    public String get_zipcode() {
        return _zipcode;
    }

    /**
     * @param _zipcode
     *            The _zipcode to set.
     */
    public void set_zipcode(String _zipcode) {
        this._zipcode = _zipcode;
    }
}
