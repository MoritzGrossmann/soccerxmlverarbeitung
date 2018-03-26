package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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

    public abstract Result push(Entity entity) throws WrongEntityTypeException;

    public abstract Result delete(Entity entity);

    public abstract boolean contains(Entity entity);
}
