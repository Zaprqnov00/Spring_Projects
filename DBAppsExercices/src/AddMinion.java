import java.sql.*;
import java.util.Scanner;

public class AddMinion implements Implementation{

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        Connection connection = Utils.getSQLConnection();

        String[] inputMinion = scanner.nextLine().split("\\s+");
        String minionName = inputMinion[1];
        int minionAge = Integer.parseInt(inputMinion[2]);
        String minionTown = inputMinion[3];

        String[] inputVillain = scanner.nextLine().split("\\s+");
        String villainName = inputVillain[1];

        PreparedStatement addTownStmt = connection.prepareStatement(SELECT_TOWNS);
        addTownStmt.setString(1, minionTown);
        ResultSet townSet = addTownStmt.executeQuery();

        if (!townSet.next()) {
            PreparedStatement insertTown = connection.prepareStatement(INSERT_TOWN_IF_NOT_EXIST);
            insertTown.setString(1, minionTown);
            insertTown.executeUpdate();

            System.out.printf("Town %s was added to the database.\n", minionTown);
        }

        int townID = 0;
        PreparedStatement selectTownIDStmt = connection.prepareStatement(SELECT_TOWN_ID);
        selectTownIDStmt.setString(1, minionTown);
        ResultSet resultTownID = selectTownIDStmt.executeQuery();

        while (resultTownID.next()) {
            townID = resultTownID.getInt(GET_ID);
        }

        PreparedStatement addVillainStmt = connection.prepareStatement(SELECT_VILLAINS);
        addVillainStmt.setString(1, villainName);
        ResultSet villainSet = addVillainStmt.executeQuery();

        if (!villainSet.next()) {
            PreparedStatement insertVillain = connection.prepareStatement(INSERT_VILLAINS_IF_NOT_EXIST);
            insertVillain.setString(1, villainName);
            insertVillain.setString(2, "evil");
            insertVillain.executeUpdate();

            System.out.printf("Villain %s was added to the database.\n", villainName);
        }

        PreparedStatement addMinionStmt = connection.prepareStatement(ADD_MINION);
        addMinionStmt.setString(1, minionName);
        addMinionStmt.setInt(2, minionAge);
        addMinionStmt.setInt(3, townID);
        addMinionStmt.executeUpdate();

        PreparedStatement selectMinionIDStmt = connection.prepareStatement(SELECT_MINION_ID);
        selectMinionIDStmt.setString(1, minionName);
        ResultSet minionIDSet = selectMinionIDStmt.executeQuery();

        int minionID = 0;
        if (minionIDSet.next()) {
            minionID = minionIDSet.getInt(GET_ID);
        }

        PreparedStatement selectVillainIDStmt = connection.prepareStatement(SELECT_VILLAIN_ID);
        selectVillainIDStmt.setString(1, villainName);
        ResultSet villainIDSet = selectVillainIDStmt.executeQuery();

        int villainID = 0;
        if (villainIDSet.next()){
            villainID = villainIDSet.getInt(GET_ID);
        }

        PreparedStatement villainAndMinionStmt = connection.prepareStatement(INSERT_MINION_TO_VILLAIN);
        villainAndMinionStmt.setInt(1, minionID);
        villainAndMinionStmt.setInt(2, villainID);
        villainAndMinionStmt.executeUpdate();

        System.out.printf("Successfully added %s to be minion of %s.\n", minionName, villainName);

        connection.close();
    }
}
