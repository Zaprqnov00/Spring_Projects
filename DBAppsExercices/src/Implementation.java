public interface Implementation {

    String UPDATE_TOWNS = "UPDATE `towns`" +
            " SET `name` = upper(`name`)" +
            " WHERE `country` = ?";
    String SELECT_TOWNS_NAME = "SELECT `name`" +
            " FROM `towns`" +
            " WHERE `country` = ?";
    String GET_NAME = "name";
    String NO_TOWNS_AFFECTED = "No town names were affected.";
    String PRINT_FORMAT_AFFECTED_TOWNS = "%d town names were affected.\n";
    String GET_VILLAIN_NAME_BY_ID = "SELECT v.`name`" +
            " FROM `villains` AS v" +
            " WHERE v.`id` = ?";
    String GET_MINIONS_BY_VILLAIN_ID = "SELECT m.`name`, m.`age`" +
            " FROM `minions` AS m" +
            " JOIN `minions_villains` AS mv ON m.id = mv.minion_id" +
            " WHERE mv.`villain_id` = ?";
    String WRONG_VILLAIN_ID_PRINT_FORMAT = "No villain with ID %d exists in the database.";
    String PRINT_VILLAIN_NAME = "Villain: %s\n";
    String GET_MINION_AGE = "age";
    String PRINT_MINIONS_FORMAT = "%d. %s %d\n";
    String GET_VILLAINS_AND_MINION_COUNT = "SELECT v.`name`, COUNT(distinct mv.`minion_id`) " +
            " as 'minions_count'" +
            " FROM `villains` as v" +
            " JOIN `minions_villains` as mv ON v.`id` = mv.`villain_id`" +
            " GROUP BY `villain_id`" +
            " HAVING `minions_count` > ?" +
            " ORDER BY `minions_count` DESC;";
    String GET_MINIONS_COUNT = "minions_count";
    String PRINT_FORMAT = "%s %d";
    String UPDATE_MINIONS = "UPDATE `minions`" +
            " SET `name` = lower(`name`), `age` = `age` + 1" +
            " WHERE `id` = ?";
    String SELECT_MINIONS = "SELECT `name`, `age`" +
            " FROM `minions`";
    String GET_AGE = "age";
    String PRINT_FORMAT_WITH_NEW_SPACE = "%s %d\n";
    String GET_ALL_MINIONS_NAMES = "SELECT `name`" +
            " FROM `minions`";
    String GET_VILLAIN_NAME = "SELECT `name`" +
            " FROM `villains`" +
            " WHERE `id` = ?";
    String GET_COUNT_MINIONS = "SELECT count(`villain_id`) AS 'count_minions'" +
            " FROM `minions_villains`" +
            " WHERE `villain_id` = ?";
    String DELETE_ALL_MINIONS = "DELETE mv" +
            " FROM `minions_villains` AS mv" +
            " WHERE `villain_id` = ?";
    String DELETE_VILLAIN_BY_ID = "DELETE v" +
            " FROM `villains` AS v" +
            " WHERE `id` = ?";
    String NO_SUCH_VILLAIN_PRINT = "No such villain was found";
    String GET_MINIONS_COUNTS = "count_minions";
    String WAS_DELETED_PRINT_FORMAT = "%s was deleted\n";
    String COUNT_MINIONS_PRINT_FORMAT = "%d minions released";

    String PROCEDURE_FOR_MINION_INCREASE_AGE = "CREATE PROCEDURE usp_get_older(minion_id INT)" +
            "BEGIN" +
            "  UPDATE `minions` AS m" +
            "    SET `age` = `age` + 1" +
            "    WHERE m.id = ?;" +
            "END";

    String GET_ID = "id";
    String SELECT_MINION_NAME_AGE = "SELECT `name`, `age`" +
            " FROM `minions`" +
            " WHERE `id` = ?";

    String SELECT_TOWNS = "SELECT * FROM `towns`" +
            " WHERE `name` = ?";
    String SELECT_VILLAINS = "SELECT * FROM `villains`" +
            " WHERE `name` = ?";
    String INSERT_VILLAINS_IF_NOT_EXIST = "INSERT `villains`(`name`, `evilness_factor`)" +
            " VALUES (?,?)";
    String INSERT_TOWN_IF_NOT_EXIST = "INSERT `towns`(`name`)" +
            " VALUES (?)";
    String SELECT_TOWN_ID = "SELECT `id`" +
            " FROM `towns`" +
            " WHERE `name` = ?";
    String ADD_MINION = "INSERT `minions` (`name`, `age`, `town_id`)" +
            " VALUES (?,?,?)";
    String SELECT_MINION_ID = "SELECT `id`" +
            " FROM `minions`" +
            " WHERE `name` = ?";
    String SELECT_VILLAIN_ID = "SELECT `id`" +
            " FROM `villains`" +
            " WHERE `name` = ?";
    String INSERT_MINION_TO_VILLAIN = "INSERT `minions_villains` (`minion_id`, `villain_id`)" +
            " VALUES (?,?)";
}
