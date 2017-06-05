package soccer;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by mehdithreem on 6/5/2017 AD.
 */
public class ConnectionFactory {
    private static String driver = "org.postgresql.Driver";
    private static String connectionString = "jdbc:postgresql://localhost:5432/soccer";
    private static String username = "soccer_access";
    private static String password = "soccerpass";

    public static Connection getConnection() {
        Connection c = null;
        try {
            Class.forName(driver);
            c = DriverManager
                    .getConnection(connectionString, username, password);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
        }

        return c;
    }
}
