import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetVillainsNames implements Implementation{


    public static void main(String[] args) throws SQLException {

        Connection connection = Utils.getSQLConnection();

        PreparedStatement statement = connection.prepareStatement(GET_VILLAINS_AND_MINION_COUNT);
        statement.setInt(1,15);

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            String villainName = resultSet.getString(GET_NAME);
            int minionCount = resultSet.getInt(GET_MINIONS_COUNT);

            System.out.printf(PRINT_FORMAT,villainName, minionCount);
        }

        connection.close();
    }
}
