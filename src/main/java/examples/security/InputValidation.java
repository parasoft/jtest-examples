package examples.security;

import java.io.IOException;
import java.io.PrintStream;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * This example shows how enforcing BD.SECURITY.VPPD: ("Encapsulate all
 * dangerous data returning methods with a validation function") helps to
 * definitely secure an application from dangerous inputs.
 * </p>
 * <p>
 * The main security vulnerability in this code is that the username and
 * password entered by the user are not validated. This is very dangerous, for
 * example anybody entering <code>"a' OR 'a' = 'a"</code> as username and
 * password would be authorized.
 * </p>
 * To go over this example perform the following steps:
 * <ol>
 * <li>Run "Security Assessment" on this file. Note that the security
 * vulnerability is reported by both BD.SECURITY.TDSQL and BD.SECURITY.VPPD.
 * BD.SECURITY.TDSQL tell us that tainted data originates in the
 * <code>HttpServletRequest</code> and propagates without being validate to the
 * SQL statement. BD.SECURITY.VPPD tell us that <code>HttpServletRequest</code>
 * is returning tainted data that is not encapsulated by a validating
 * method.</li>
 * <li>Fix the problem reported by uncommenting the <code>PARTIAL FIX</code> in
 * the <code>authorize()</code> method.</li>
 * <li>Rerun "Security Assessment".</li>
 * </ol>
 * Note that the BD.SECURITY.TDSQL errors are no longer reported, but the
 * BD.SECURITY.VPPD errors are still reported. One could leave the code as it is
 * as the fix successfully prevents any attack on the existing code. To see how
 * this could be a problem imagine that later on somebody does the code change
 * in <code>CODE ENHANCEMENT</code> in <code>doGet()</code>. After that change
 * the code would be vulnerable to an attack again. A user would still not be
 * able to get authorized by just entering <code>"a' OR 'a' = 'a"</code> as the
 * previous fix would prevent that, but somebody could instead enter
 * <code>"hacker"</code> for username and
 * <code>"unknown'; DELETE FROM Accounts; --"</code> for password and completely
 * delete the "Accounts" table (or any other table) from the database.
 * <p>
 * A more general fix is the one shown in <code>DEFINITIVE FIX</code> in
 * <code>doGet()</code>, the input is validated as soon as it enters the code
 * and before it starts propagating through different paths.
 * </p>
 */
public class InputValidation extends HttpServlet {

    private Connection _connection;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintStream output = new PrintStream(response.getOutputStream());
        // get username/password entered by the user
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        // DEFINITIVE FIX: replace the 2 lines above by:
        // String username = validate(request.getParameter("username"));
        // String password = validate(request.getParameter("password"));
        if (!authorize(username, password)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            // CODE ENHANCEMENT: keep track of invalid login attemps:
            // logInvalidLogin(username, password);
            return;
        }
        output.println("<h1>Welcome " + username + "</h1>");
    }

    /**
     * @return true if user is authorized
     */
    private boolean authorize(String username, String password) {
        Statement stmt = null;
        ResultSet rs = null;
        try {
            // PARTIAL FIX: validate username and password before using:
            // username = validate(username);
            // password = validate(password);
            String query = "SELECT * FROM users WHERE username='" + username + "' AND password='" + password + "'";
            stmt = _connection.createStatement();
            rs = stmt.executeQuery(query);
            if (rs.next()) {
                // user credentials exists, user authorized
                return true;
            }
            // user not authorized
            return false;
        } catch (Throwable t) {
            // do not authorize if any error happens
            return false;
        }
    }

    /**
     * Minimal validation of username/password Strings for demonstration.
     * 
     * @return the original username/password if valid
     * @throws IllegalArgumentException
     *             if invalid username or password
     */
    static String validate(String username_or_password) {
        if (username_or_password.indexOf('\'') != -1) {
            throw new IllegalArgumentException("invalid input: " + username_or_password);
        }
        return username_or_password;
    }

    void logInvalidLogin(String username, String password) {
        try {
            Statement stmt = _connection.createStatement();
            String query = "INSERT INTO invalid_logins VALUES ('" + username + "', '" + password + "')";
            stmt.execute(query);
        } catch (SQLException e) {
            // ignore
        }
    }
}
