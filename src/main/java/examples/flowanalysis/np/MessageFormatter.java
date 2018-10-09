package examples.flowanalysis.np;

/**
 * Class that demonstrate analysis across multiple functions and classes.
 */
public class MessageFormatter {

    public static void printMessage(DatabaseObject obj) {
        printMessage(obj.getClassName(), obj.getOID());
    }

    public static void printMessage(Object className, Object oid) {
        System.out.println("[" + className.toString() + "] " + oid.toString());
    }
}
