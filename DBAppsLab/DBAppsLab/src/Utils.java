import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

enum Utils {
    ;
    static Connection getSQLConnection() throws SQLException {
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "12300012");

        //jdbc:mysql://localhost:3306/soft_uni
        //jdbc:mysql://localhost:3306/diablo
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/soft_uni", properties);
    }
}
