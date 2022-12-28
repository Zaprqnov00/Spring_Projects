import jdk.jshell.execution.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class IncreaseAgeStoredProcedure implements Implementation{


    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        Connection connection = Utils.getSQLConnection();

        int minionID = Integer.parseInt(scanner.nextLine());

        PreparedStatement minionStatement = connection.prepareStatement(PROCEDURE_FOR_MINION_INCREASE_AGE);
        minionStatement.setInt(1, minionID);
        minionStatement.executeUpdate();

        PreparedStatement selectMinionStatement = connection.prepareStatement(SELECT_MINION_NAME_AGE);
        selectMinionStatement.setInt(1,minionID);

        ResultSet resultSet = selectMinionStatement.executeQuery();

        while (resultSet.next()) {
            String minionName = resultSet.getString(GET_NAME);
            int minionAge = resultSet.getInt(GET_AGE);
            System.out.println(minionName + " " + minionAge);
        }

    }
}
