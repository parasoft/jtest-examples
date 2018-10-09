package examples.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import junitparams.Parameters;
import junitparams.JUnitParamsRunner;
import org.junit.runner.RunWith;

/**
 * Parasoft Jtest UTA: Test class for NaiveStringBuilder
 *
 * @see examples.junit.NaiveStringBuilder
 */
@RunWith(JUnitParamsRunner.class)
public class NaiveStringBuilderParameterizedTest
{

    /**
     * Parasoft Jtest UTA: Test for append(String)
     *
     * @see examples.junit.NaiveStringBuilder#append(String)
     */
    @Test
    @Parameters(method = "testAppend_Parameters")
    public void testAppend(String value, String sTitle)
        throws Throwable
    {
        // given
        NaiveStringBuilder underTest = NaiveStringBuilder.createInstance(value);
        // when 
        String result = underTest.append(sTitle);
        // then
        String expectedResult = value+sTitle; 
        assertEquals(expectedResult, result);
    }

    // Parasoft Jtest UTA: Initialize test parameters
    @SuppressWarnings("unused")
    private static Object[][] testAppend_Parameters()
        throws Throwable
    {
        // Parameters: value={0}, sTitle={1}
        return new Object[][] { 
            { " ", "01234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890"},
            { "", "hello world"}, 
            { "hello world", " "},
            { "01234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890", null}, 
            { null, ""}};
    }
}