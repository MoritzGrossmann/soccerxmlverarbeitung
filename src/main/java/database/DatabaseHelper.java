package database;

import database.interfaces.Entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class DatabaseHelper {

    protected static final String TEAM_TABLE = "t_team";

    protected static final String GOAL_TABLE = "t_goal";

    protected static final String GROUP_TABLE = "t_group";

    protected static final String LOCATION_TABLE = "t_location";

    protected static final String MATCH_TABLE = "t_match";

    protected static final String MATCH_RESULT_TABLE = "t_match_result";

    protected static final String LEAGUE_TABLE = "t_league";

    protected static Connection createConnection() throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/soccer?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "soccer", "soccer");
        }

        return connection;
    }

    protected static Connection connection;

    public static void truncate() throws SQLException {

        Connection connection = createConnection();

        List<String> sqls = new ArrayList<>();
        sqls.add(String.format("TRUNCATE TABLE %s", GOAL_TABLE));
        sqls.add(String.format("TRUNCATE TABLE %s", MATCH_RESULT_TABLE));
        sqls.add(String.format("TRUNCATE TABLE %s", LEAGUE_TABLE));
        sqls.add(String.format("TRUNCATE TABLE %s", LOCATION_TABLE));
        sqls.add(String.format("TRUNCATE TABLE %s", GROUP_TABLE));
        sqls.add(String.format("TRUNCATE TABLE %s", MATCH_TABLE));
        sqls.add(String.format("TRUNCATE TABLE %s", TEAM_TABLE));

        sqls.forEach(sql -> {
            try {
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.execute();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        });
    }

    public abstract Result push(Entity entity) throws WrongEntityTypeException;

    public abstract Result delete(Entity entity);

    public abstract boolean contains(Entity entity);
}
