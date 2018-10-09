package examples.junit;

/**
 * A class that shows that object creation will be performed using a factory
 * method.
 */
public class NaiveStringBuilder {

    private String _stringValue;

    private NaiveStringBuilder(String initialValue) {
        _stringValue = initialValue;
    }

    public static NaiveStringBuilder createInstance(String value) {
        return new NaiveStringBuilder(value);
    }

    String append(String sTitle) {
        if (sTitle == null) {
            throw new IllegalArgumentException();
        }
        _stringValue += sTitle;
        return _stringValue;
    }
}
