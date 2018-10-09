package examples.junit;

/**
 * A class that shows generating test cases for inherited methods.
 */
public class CustomMoney extends Money {

    public CustomMoney(int amount, String currency) {
        super(amount, currency);
    }

    public void changeCurrency(String currency) {
        fCurrency = currency;
    }
}
