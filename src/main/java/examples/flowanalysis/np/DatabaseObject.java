package examples.flowanalysis.np;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Class that demonstrate analysis across multiple functions and classes.
 */
public class DatabaseObject {

    Object oid = null;

    public static DatabaseObject getObjectFromDatabase(Connection sqlConnection, String table, String id) {
        DatabaseObject obj = new DatabaseObject();
        String sQuery = "SELECT * FROM " + table + " WHERE id='" + id + "'";
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = sqlConnection.createStatement();
            rs = stmt.executeQuery(sQuery);
            if (rs.next()) {
                obj.oid = rs.getString("oid");
            }
        } catch (SQLException sqle) {
            // report exception
        } finally {
            close(rs);
            close(stmt);
        }
        MessageFormatter.printMessage(obj);
        return obj;
    }

    public String getClassName() {
        return this.getClass().getName();
    }

    public Object getOID() {
        return oid;
    }

    private static void close(ResultSet rs) {
        if (rs == null) {
            return;
        }
        try {
            rs.close();
        } catch (Exception e) {
        }
    }

    private static void close(Statement stmt) {
        if (stmt == null) {
            return;
        }
        try {
            stmt.close();
        } catch (Exception e) {
        }
    }
}
