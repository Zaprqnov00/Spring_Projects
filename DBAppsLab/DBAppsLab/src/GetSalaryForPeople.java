import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class GetSalaryForPeople {

    private static final String GET_NAMES_PEOPLE = " SELECT `first_name`, `last_name`" +
            " FROM `employees` AS e" +
            " WHERE e.`salary` > ?";

    private static final String PRINT_FORMAT = "%s %s\n";

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        Connection connection = Utils.getSQLConnection();

        PreparedStatement statement = connection.prepareStatement(GET_NAMES_PEOPLE);

        double salary = Double.parseDouble(scanner.nextLine());

        statement.setDouble(1, salary);

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");

            System.out.printf(PRINT_FORMAT,firstName,lastName);
        }

    }
}
