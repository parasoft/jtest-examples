package examples.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExampleServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        if (request.getParameter("name").equals("John"))
            writer.println("name parameter is John");
        else
            writer.println("name is not John");
    }

    public void tryThis(ServletContext sc) {
        String hello = "hello";
        String result = sc.getInitParameter("hello");
        System.out.println(result);
        int n = result.length();
        if (n == 0) {
            String pr = hello + result;
        } else if (n > 0) {
            String pr = result + hello;
        }
        Enumeration e = sc.getInitParameterNames();
        while (e.hasMoreElements()) {
            Object o = e.nextElement();
            System.out.println(o.toString());
        }
    }
}
