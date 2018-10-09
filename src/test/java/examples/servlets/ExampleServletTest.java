package examples.servlets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Enumeration;

import javax.servlet.ServletContext;

import org.junit.Test;

public class ExampleServletTest
{
    /**
     * Test for method: tryThis(javax.servlet.ServletContext)
     * 
     * @throws Throwable Tests may throw any Throwable
     * @see ExampleServlet#tryThis(javax.servlet.ServletContext)
     * @author Parasoft Jtest 10.0
     */
    @Test
    public void testTryThis1()
        throws Throwable
    {
        ExampleServlet THIS = new ExampleServlet();
        try {
            THIS.tryThis((ServletContext)null);
        } catch (NullPointerException npe) {
            // expected
            return;
        }
        fail();
    }

    /**
     * Test for method: tryThis(javax.servlet.ServletContext)
     * 
     * @throws Throwable Tests may throw any Throwable
     * @see ExampleServlet#tryThis(javax.servlet.ServletContext)
     * @author Parasoft Jtest 10.0
     */
    @Test
    public void testTryThis2()
        throws Throwable
    {

        ExampleServlet THIS = new ExampleServlet();
        ServletContext context = mock(ServletContext.class);
        when(context.getInitParameter(anyString())).thenReturn(null);
        try {
            THIS.tryThis(context);
        } catch (NullPointerException npe) {
            // expected
            return;
        }
        fail();
    }

    /**
     * Test for method: tryThis(javax.servlet.ServletContext)
     * 
     * @throws Throwable Tests may throw any Throwable
     * @see ExampleServlet#tryThis(javax.servlet.ServletContext)
     * @author Parasoft Jtest 10.0
     */
    @Test
    public void testTryThis3()
        throws Throwable
    {
        ExampleServlet THIS = new ExampleServlet();

        ServletContext context = mock(ServletContext.class);
        when(context.getInitParameter(anyString())).thenReturn("");
        Enumeration enumeration = mock(Enumeration.class);
        when(enumeration.hasMoreElements()).thenReturn(false);
        when(context.getInitParameterNames()).thenReturn(enumeration);

        THIS.tryThis(context);
        assertEquals(null, THIS.getServletConfig());
        assertEquals("", THIS.getServletInfo());
    }

    /**
     * Test for method: tryThis(javax.servlet.ServletContext)
     * 
     * @throws Throwable Tests may throw any Throwable
     * @see ExampleServlet#tryThis(javax.servlet.ServletContext)
     * @author Parasoft Jtest 10.0
     */
    @Test
    public void testTryThis4()
        throws Throwable
    {
        ExampleServlet THIS = new ExampleServlet();
        ServletContext var1 = mock(ServletContext.class);
        when(var1.getInitParameter(anyString())).thenReturn("0");
        Enumeration enumeration = mock(Enumeration.class);
        when(enumeration.hasMoreElements()).thenReturn(true, true, false);
        when(enumeration.nextElement()).thenReturn("hello world", 5);

        when(var1.getInitParameterNames()).thenReturn(enumeration);

        THIS.tryThis(var1);
        assertEquals(null, THIS.getServletConfig());
        assertEquals("", THIS.getServletInfo());
    }

}
