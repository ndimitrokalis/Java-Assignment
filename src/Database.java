import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;

/**
 * Starts a connection with the database,
 * uses a MySQL database at localhost
 */

public class Database {
    public static Connection getConnection() {
        ErrorLogger errorLogger = new ErrorLogger();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz_game_java", "root", "");
            return c;
        } catch (ClassNotFoundException e) {
            errorLogger.error(new Date() + "," + Database.class.getSimpleName() + ", " + e.getMessage());
            System.out.println("\nError: Missing Database driver");
        } catch (SQLException e) {
            errorLogger.error(new Date() + "," + Database.class.getSimpleName() + ", " + e.getMessage());
            System.out.println("\nError: Invalid Query");
        }
        return null;
    }
}
