package actions.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static actions.commons.DBConstants.*;
public class DBConnection {
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Database connection closed.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
