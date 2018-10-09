/*
 * Created on Dec 27, 2004
 */
package examples.stackmachine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Command line driver for the Stack Machine.
 */
public class CommandLineStackMachine {

    private static AbstractStackMachine _stack = new LifoStackMachine();

    private static void displayMenu() {
        System.out.println("Command Line Stack Machine");
        System.out.println("  (A)dd");
        System.out.println("  (S)ubtract");
        System.out.println("  (M)ultiply");
        System.out.println("  (D)ivide");
        System.out.println("  (P)op");
        System.out.println("  (E)xit");
        System.out.println("  <number to push>");
    }

    /**
     * Get the display string for the stack.
     * 
     * @return A string representation of the stack.
     */
    public static String getStack() {
        return _stack.toString();
    }

    /**
     * Get the next command from System.in.
     * 
     * @return The next command as a string.
     */
    private static String nextCommand() {
        System.out.print(">  ");
        BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
        try {
            String result = buf.readLine();
            return result;
        } catch (IOException ioe) {
            return "exit";
        }
    }

    /**
     * Apply a string command to the stack.
     * 
     * @param command
     *            A command from nextCommand().
     * @return <code>true</code> if the program should exit.
     */
    private static boolean processCommand(String command) {
        String upper = command.toUpperCase();
        if ("EXIT".equals(upper) || "E".equals(upper) || "QUIT".equals(upper) || "Q".equals(upper)) {
            return true;
        }
        if ("+".equals(command) || "A".equals(upper) || "ADD".equals(upper)) {
            _stack.add();
        } else if ("-".equals(command) || "S".equals(upper) || "SUBTRACK".equals(upper)) {
            _stack.subtract();
        } else if ("*".equals(command) || "M".equals(upper) || "MULTIPLY".equals(upper)) {
            _stack.multiply();
        } else if ("/".equals(command) || "D".equals(upper) || "DIVIDE".equals(upper)) {
            _stack.divide();
        } else if ("P".equals(upper) || "POP".equals(upper)) {
            _stack.pop();
        } else if ("?".equals(upper) || "H".equals(upper) || "help".equals(upper)) {
            displayMenu();
        } else {
            try {
                int value = Integer.parseInt(command);
                _stack.push(command);
            } catch (NumberFormatException nfe) {
                System.out.println("Error: Unknown command: " + command);
            }
        }
        return false;
    }

    /**
     * Program entry point.
     * 
     * @param args
     *            Not used.
     */
    public static void main(String[] args) {
        
        if ((args != null) && (args.length > 0)) {
            autoMode(args);
            return;
        }
        
        displayMenu();
        boolean exit;
        do {
            System.out.println(getStack());
            String command = nextCommand();
            exit = processCommand(command);
        } while (!exit);
    }
    
    private static void autoMode(String[] args) 
    {
        if (!"-auto".equals(args[0])) {
            System.err.println("Invalid option:" + args[0]);
            printUsage();
            return;
        }
        
        if (args.length <= 1) {
            System.err.println("Please specify command(s) to execute");
            printUsage();
            return;
        }
        
        System.out.println("Starting Command Line Stack Machine in auto mode");
        
        for (int i = 1; i < args.length; i++) {
            String command = args[i];
            System.out.println("Processing command " + command);
            processCommand(command);
            System.out.println(getStack());
        }
        System.out.println("Done - all commands executed");
    }
    
    static void printUsage()
    {
        System.out.println("");
        System.out.println("Command Line Stack Machine");
        System.out.println("");
        System.out.println("usage: CommandLineStackMachine [OPTION]");
        System.out.println("");
        System.out.println("   -auto <COMMANDS SEQUENCE>     enables automatic mode");
        System.out.println("                                 COMMANDS SEQUENCE is a list of commands to execute separated with space");
        System.out.println("                                 for example: CommandLineStackMachine -auto 99 99 A");
        
    }
}
