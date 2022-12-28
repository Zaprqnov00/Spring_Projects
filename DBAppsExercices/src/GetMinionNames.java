import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class GetMinionNames implements Implementation{


    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        int villainID = Integer.parseInt(scanner.nextLine());

        Connection connection = Utils.getSQLConnection();

        PreparedStatement villainNameStatement = connection.prepareStatement(GET_VILLAIN_NAME_BY_ID);
        villainNameStatement.setInt(1, villainID);

        ResultSet villainNameResult = villainNameStatement.executeQuery();

        if (!villainNameResult.next()){
            System.out.printf(WRONG_VILLAIN_ID_PRINT_FORMAT,villainID);
            return;
        }else {
            String villainName = villainNameResult.getString(GET_NAME);
            System.out.printf(PRINT_VILLAIN_NAME,villainName);
        }

        PreparedStatement minionsStatement = connection.prepareStatement(GET_MINIONS_BY_VILLAIN_ID);
        minionsStatement.setInt(1, villainID);

        ResultSet minionsResult = minionsStatement.executeQuery();

        for (int index = 0; minionsResult.next(); index++) {
            String minionName = minionsResult.getString(GET_NAME);
            int minionAge = minionsResult.getInt(GET_MINION_AGE);

            System.out.printf(PRINT_MINIONS_FORMAT,index + 1, minionName, minionAge);

        }

        connection.close();
    }
}
