/*
 * RunnableStackMachine.java uses this class as the model for the stack list.
 * RunnableStackMachine should make sure that it calls the stack implementations
 * according to the precondition contracts defined in the interface.<br>
 */
package examples.stackmachine;

public interface StackInterface {

    /**
     * Add an element to the stack.<br>
     * <code><i>Pre comment:</i></code> the String pushed should represent an
     * integer.
     * 
     * @param value
     *            to add to the stack.
     */
    public void push(String value);

    /**
     * Retrieve an element from the stack.<br>
     * <code><i>Pre comment:</i></code> 'pop()' can only be called if the stack is
     * not empty.
     */
    public void pop();
}
