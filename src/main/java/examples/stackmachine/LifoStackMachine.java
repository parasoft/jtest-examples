/*
 * Created on Sep 10, 2003
 */
package examples.stackmachine;

/** Implements Lifo schema of abstract stack. */
public class LifoStackMachine extends AbstractStackMachine {

    /**
     * @see com.parasoft.example.AbstractStack#push(java.lang.String)
     */
    public void push(String value) {
        insertElementAt(value, 0);
    }

    /**
     * @see com.parasoft.example.AbstractStack#pop()
     */
    public void pop() {
        removeElementAt(0);
    }

    /**
     * @see com.parasoft.example.AbstractStackMachine#popInt()
     */
    @Override
    protected int popInt() {
        return Integer.parseInt((String) remove(0));
    }

    /**
     * @see com.parasoft.example.AbstractStackMachine#pushInt(int)
     */
    @Override
    protected void pushInt(int value) {
        push(Integer.toString(value));
    }
}
