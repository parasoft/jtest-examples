package examples.junit;

/**
 * A class that shows that static methods will be tested in a generated test
 * case.
 */
public class IntegerComparator {

    private IntegerComparator() {
    }

    public static boolean isGreater(int val1, int val2) {
        return val1 > val2;
    }
}
