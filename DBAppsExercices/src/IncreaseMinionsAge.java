import jdk.jshell.execution.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class IncreaseMinionsAge implements Implementation{

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        Connection connection = Utils.getSQLConnection();

        PreparedStatement statement = connection.prepareStatement(UPDATE_MINIONS);

        String[] minionsIDs = scanner.nextLine().split(" ");

        for (int i = 0; i < minionsIDs.length; i++) {
            int currentID = Integer.parseInt(minionsIDs[i]);
            statement.setInt(1,currentID);

            statement.executeUpdate();
        }

        PreparedStatement minionsStatement = connection.prepareStatement(SELECT_MINIONS);
        ResultSet minionsSet = minionsStatement.executeQuery();

        while (minionsSet.next()) {
            String minionName = minionsSet.getString(GET_NAME);
            int minionAge = minionsSet.getInt(GET_AGE);

            System.out.printf(PRINT_FORMAT_WITH_NEW_SPACE,minionName,minionAge);
        }

        connection.close();
    }
}
