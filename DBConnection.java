import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/CineBook";
        String username = "root";
        String password = "123456789";

        return DriverManager.getConnection(url, username, password);
    }

    public static void main(String[] args) {
        try {
            Connection con = getConnection();
            System.out.println("MySQL Connected Successfully!");
            con.close();
        } catch (SQLException e) {
            System.out.println("Connection Failed!");
            System.out.println(e.getMessage());
        }
    }
}