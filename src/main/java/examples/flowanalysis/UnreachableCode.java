package examples.flowanalysis;

/**
 * Class that demonstrates application of "Unreachable code" rules.
 */
public class UnreachableCode {

    public void printCalculationResult(Object result) {
        if (result instanceof Exception) {
            System.err.println("Exception found: " + ((Exception) result).getMessage());
        } else if (result instanceof RuntimeException) { // BD.UC.COND
                                                         // VIOLATION
            ((RuntimeException) result).printStackTrace(System.err);
        } else {
            System.out.println("Result: " + result.toString());
        }
    }

    public void guessFigure(boolean round, boolean volumetric) {
        final int figure;
        if (round && volumetric) {
            figure = SPHERE;
        } else if (round && !volumetric) {
            figure = CIRCLE;
        } else if (!round && volumetric) {
            figure = CUBE;
        } else {
            figure = SQUARE;
        }
        switch (figure) {
        case SPHERE:
            System.out.println("This is a sphere");
            break;
        case HIMESPHERE:
            System.out.println("This is a hemispere"); // BD.UC.SWITCH
                                                       // VIOLATION
            break;
        case CIRCLE:
            System.out.println("This is a circle");
            break;
        case CUBE:
            System.out.println("This is a cube");
            break;
        default:
            System.out.println("This is a square");
            break;
        }
    }

    private static final int SPHERE = 0;

    private static final int HIMESPHERE = 1;

    private static final int CIRCLE = 2;

    private static final int CUBE = 3;

    private static final int SQUARE = 4;
}
