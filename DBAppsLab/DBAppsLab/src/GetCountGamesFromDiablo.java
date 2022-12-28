import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class GetCountGamesFromDiablo {

    private static final String GET_USER_COUNT_GAMES = "SELECT " +
            "`user_name`, `first_name`, `last_name`,(SELECT COUNT(*) FROM `users_games` AS ug WHERE user_id = u.id) AS 'count_games'\n" +
            "FROM `users` AS u\n" +
            "WHERE user_name = ?";

    private static final String PRINT_USERS_INFORMATION = "User: %s\n%s %s has played %s games";
    private static final String NO_SUCH_USER = "No such user exists";

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        Connection connection = Utils.getSQLConnection();

        PreparedStatement statement = connection.prepareStatement(GET_USER_COUNT_GAMES);

        String user = scanner.nextLine();

        statement.setString(1,user);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()){
            String userName = resultSet.getString("user_name");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String countGames = resultSet.getString("count_games");

            System.out.printf(PRINT_USERS_INFORMATION,
                    userName,firstName,lastName,countGames);
        }else {
            System.out.println(NO_SUCH_USER);
        }

    }
}
