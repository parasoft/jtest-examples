package examples.mock;

/**
 * Test class for ConstructorExample
 */
 
import static org.junit.Assert.assertEquals;

import java.io.RandomAccessFile;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import examples.mock.ConstructorExample;

/**
 * ConstructorExampleTest is a unit test class for class ConstructorExample.
 * @see examples.mock.ConstructorExample
 * @author Parasoft Jtest
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(ConstructorExample.class)
public class ConstructorExampleTest 
{
    @Test
    public void testGetFileLength() 
		throws Exception 
	{
    	RandomAccessFile raf =  PowerMockito.mock(RandomAccessFile.class);
		PowerMockito.whenNew(RandomAccessFile.class).withAnyArguments().thenReturn(raf );
    	
		PowerMockito.when(raf.length()).thenReturn(10000000000000L);
    	
        // jtest_tested_method
        long RETVAL = ConstructorExample.getFileLength ("ignore this arg");
        assertEquals (10000000000000L, RETVAL);
    }
}