import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayDeque;

public class PrintAllMinionNames implements Implementation{

    public static void main(String[] args) throws SQLException {

        Connection connection = Utils.getSQLConnection();

        PreparedStatement statement = connection.prepareStatement(GET_ALL_MINIONS_NAMES);
        ResultSet resultSet = statement.executeQuery();

        ArrayDeque<String> deque = new ArrayDeque<>();

        while (resultSet.next()) {
            deque.add(resultSet.getString(GET_NAME));
        }

        printNames(deque);

        connection.close();

    }

    private static void printNames(ArrayDeque<String> deque) {
        for (int i = 0; i < deque.size(); i++) {
            System.out.println(deque.pollFirst());
            System.out.println(deque.pollLast());
        }
    }
}
