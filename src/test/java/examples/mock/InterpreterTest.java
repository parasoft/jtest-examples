package examples.mock;

/*
 * InterpreterTest.java Created by Jtest on 7/3/14 2:47:55 PM.
 */

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.io.DataInputStream;
import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import examples.mock.Interpreter;

/**
 * InterpreterTest is a test class for Interpreter
 * 
 * @see Interpreter
 * @author Parasoft Jtest 10.0
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(Interpreter.class)
public class InterpreterTest
{
    @Test
    public void testAdd34()
        throws Exception
    {
        DataInputStream stream = PowerMockito.mock(DataInputStream.class);
        doReturn("ADD").when(stream).readUTF();
        when(stream.readInt()).thenReturn(3, 4);
        PowerMockito.whenNew(DataInputStream.class).withAnyArguments().thenReturn(stream);

        Interpreter itp = new Interpreter(null);
        try {
            int value = itp.getNext().intValue();
            assertTrue("value is:" + value, value == 7);
        } catch (IOException e) {
            fail(e.toString());
        }
    }
}
