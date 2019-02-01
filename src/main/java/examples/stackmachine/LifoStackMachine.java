/*
 * Created on Sep 10, 2003
 */
package examples.stackmachine;

/** Implements Lifo schema of abstract stack. */
public class LifoStackMachine extends AbstractStackMachine {

    /**
     * Add an element to the end of the stack.
     * @param value of the element to push in.
     */
    public void push(String value) {
        insertElementAt(value, 0);
    }

    /**
     * Retrieve an element from the stack.
     */
    public void pop() {
        removeElementAt(0);
    }

    /**
     * @see examples.stackmachine.AbstractStackMachine#popInt()
     */
    @Override
    protected int popInt() {
        return Integer.parseInt((String) remove(0));
    }

    /**
     * @see examples.stackmachine.AbstractStackMachine#pushInt(int)
     */
    @Override
    protected void pushInt(int value) {
        push(Integer.toString(value));
    }
}
