import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChangeTownNamesCasing implements Implementation{

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        String country = scanner.nextLine();

        Connection connection = Utils.getSQLConnection();

        PreparedStatement updateTownsStatement = connection.prepareStatement(UPDATE_TOWNS);
        updateTownsStatement.setString(1, country);

        int countTownsUpdate = updateTownsStatement.executeUpdate();

        if (countTownsUpdate == 0){
            System.out.println(NO_TOWNS_AFFECTED);
            return;
        }else {
            System.out.printf(PRINT_FORMAT_AFFECTED_TOWNS,countTownsUpdate);
        }

        PreparedStatement selectTownsWithUpdateStatement = connection.prepareStatement(SELECT_TOWNS_NAME);
        selectTownsWithUpdateStatement.setString(1,country);

        ResultSet townsAffectResult = selectTownsWithUpdateStatement.executeQuery();

        List<String> townsUpdatedList = new ArrayList<>();

        while (townsAffectResult.next()){
         townsUpdatedList.add(townsAffectResult.getString(GET_NAME));
        }

        System.out.println(townsUpdatedList);

        connection.close();
    }
}
