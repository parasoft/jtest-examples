package examples.junit;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import org.junit.Test;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runner.RunWith;

/**
 * Parasoft Jtest UTA: Test class for MoneyBag
 *
 * @see examples.junit.MoneyBag
 */
@RunWith(Parameterized.class)
public class MoneyBagParameterizedTest
{
    private int factor;
    private int initialValue;

    public MoneyBagParameterizedTest(int initialValue, int factor)
    {
        this.initialValue = initialValue;
        this.factor = factor;
    }
    
    /**
     * Parasoft Jtest UTA: Test for multiply(int)
     *
     * @see examples.junit.MoneyBag#multiply(int)
     */
    @Test
    public void testMultiply()
        throws Throwable
    {
        // given
        MoneyBag underTest = new MoneyBag();
        underTest.appendMoney(new Money(initialValue, "USD"));

        MoneyBag expected = new MoneyBag();
        expected.appendMoney(new Money(factor*initialValue, "USD"));
        
        // when
        IMoney result = underTest.multiply(factor);
        
        // then
        assertEquals(expected, result);
    }

    // Parasoft Jtest UTA: Initialize test parameters
    @Parameters(name = "Run {index}: factor={0}")
    public static Iterable<Object[]> data()
        throws Throwable
    {
        return Arrays.asList(new Object[][] { 
                {0, 2147483647 }, 
                {0, -2147483648 }, 
                {0, -1 }, 
                {0, 0 }, 
                {0, 1 },
                {1, 2147483647 }, 
                {1, -2147483648 }, 
                {1, -1 }, 
                {1, 0 }, 
                {1, 1 },
                {13, 2147483647 }, 
                {13, -2147483648 }, 
                {13, -1 }, 
                {13, 0 }, 
                {13, 1 }
            }
        );
    }
}