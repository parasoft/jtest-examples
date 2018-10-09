package examples.flowanalysis;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;

public class SQLInjection {

    /**
     * @param request
     * @param sqlConnection
     */
    public void auth(HttpServletRequest request, Connection sqlConnection) {
        String sUserName = request.getParameter("login");
        String sPassword = request.getParameter("passwd");
        String sQuery = "SELECT * FROM users WHERE name='" + sUserName + "' AND password='" + sPassword + "'";
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = sqlConnection.createStatement();
            rs = stmt.executeQuery(sQuery);
            if (rs.next()) {
                // user was found, authenticate, using data received
            } else {
                // no user info was found, report incorrect login and show
                // relogin form
            }
        } catch (SQLException sqle) {
            // report exception
        } finally {
            close(rs);
            close(stmt);
        }
    }

    private void close(ResultSet rs) {
        if (rs == null) {
            return;
        }
        try {
            rs.close();
        } catch (Exception e) {
        }
    }

    private void close(Statement stmt) {
        if (stmt == null) {
            return;
        }
        try {
            stmt.close();
        } catch (Exception e) {
        }
    }
}
