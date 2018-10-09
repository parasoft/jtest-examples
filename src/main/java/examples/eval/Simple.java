/*
 * @author staff This is a class showcasing the ability of Jtest. This code has
 * a number of Coding Standards errors developers commonly make.
 */
package examples.eval;

/**
 * A class containing some common coding standards violations and software
 * exceptions.
 * 
 * This is a utility class and is not meant to be instantiated.
 */
public class Simple {

    /**
     * Takes an index, and returns the appropriate map for the index.
     * 
     * @param index
     * @return map for the index
     */
    public static int map(int index) {
        switch (index) {
        case 0:
            case10: return -1;
        case 2:
        case 20:
            break;
        default:
            return -2;
        }
        return 0;
    }

    /**
     * Takes two arguments and returns the boolean result if 'str' starts with the
     * string, 'match'.
     * 
     * This method throws NullPointerExceptions when either argument to the method
     * is null -- this may or may not be by design.
     * 
     * 'startsWith' also throws a StringIndexOutOfBoundsException when 'str' is a
     * string with less length than 'match' - this is most definitely a software
     * exception.
     * 
     * A method should be able to handle null arguments gracefully, declare
     * preconditions that the method does not expect null arguments from the caller,
     * or in the least, it should document that nullpointer exceptions are possible.
     * 
     * Developers must consider the cases when exceptions could happen in the code.
     * Especially code in the public api.
     * 
     * @param str
     *            the string we want to search in
     * @param match
     *            the substring we're searching for
     * @return true if 'str' starts with 'match', false otherwise.
     * 
     *         Finding the set of arguments that cause software exceptions is hard
     *         if done by hand but is easy for Jtest. It will test tirelessly and
     *         will never get bored of testing.
     */
    public static boolean startsWith(String str, String match) {
        for (int i = 0; i < match.length(); ++i)
            if (str.charAt(i) != match.charAt(i))
                return false;
        return true;
    }
}
