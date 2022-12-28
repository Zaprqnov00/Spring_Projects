
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class RemoveVillain implements Implementation{

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        Connection connection = Utils.getSQLConnection();

        int villainID = Integer.parseInt(scanner.nextLine());

        PreparedStatement villainStatement = connection.prepareStatement(GET_VILLAIN_NAME);
        villainStatement.setInt(1,villainID);

        ResultSet villainNameResult = villainStatement.executeQuery();

        if (!villainNameResult.next()){
            System.out.println(NO_SUCH_VILLAIN_PRINT);
            return;
        }else {
            String villainName = villainNameResult.getString(GET_NAME);
            System.out.printf(WAS_DELETED_PRINT_FORMAT,villainName);
        }

        PreparedStatement minionStatement = connection.prepareStatement(GET_COUNT_MINIONS);
        minionStatement.setInt(1,villainID);

        ResultSet minionsCountResult = minionStatement.executeQuery();

        if (minionsCountResult.next()) {
            int minionsCount = minionsCountResult.getInt(GET_MINIONS_COUNTS);
            System.out.printf(COUNT_MINIONS_PRINT_FORMAT,minionsCount);
        }

        PreparedStatement deleteMinionsStatement = connection.prepareStatement(DELETE_ALL_MINIONS);
        deleteMinionsStatement.setInt(1,villainID);
        deleteMinionsStatement.executeUpdate();

        PreparedStatement deleteVillainStatement = connection.prepareStatement(DELETE_VILLAIN_BY_ID);
        deleteVillainStatement.setInt(1,villainID);
        deleteVillainStatement.executeUpdate();

    }
}
