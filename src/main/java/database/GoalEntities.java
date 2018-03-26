package database;

import models.Goal;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GoalEntities extends DatabaseHelper {

    private static GoalEntities instance;

    private GoalEntities() {

    }

    public static GoalEntities getInstance()
    {
        if (instance ==null) {
            instance = new GoalEntities();
        }

        return instance;
    }

    private static final String ID_PROPERTY = "GoalID";

    private static final String GOAL_GETTER_ID_PROPERTY = "GoalGetterID";

    private static final String GOAL_GETTER_NAME_PROPERTY = "GoalGetterName";

    private static final String IS_OVERTIME_PROPERTY = "IsOverTime";

    private static final String IS_OWN_GOAL_PROPERTY = "IsOwnGoal";

    private static final String IS_PENALTY_PROPERTY = "IsPenalty";

    private static final String MATCH_MINUTE_PROPERTY = "MatchMinute";

    private static final String SCORE_TEAM_1_PROPERTY = "ScoreTeam1";

    private static final String SCORE_TEAM_2_PROPERTY = "ScoreTeam2";

    private static final String MATCH_ID_PROPERTY = "MatchID";

    @Override
    public Result push(Entity entity) throws WrongEntityTypeException{

        if(!(entity instanceof Goal)){
            throw new WrongEntityTypeException();
        }

        try {

            Goal goal = (Goal)entity;

            Class.forName("com.mysql.jdbc.Driver").newInstance();

            connection = createConnection();

            String sql = String.format("INSERT INTO %s (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s) VALUES (?,?,?,?,?,?,?,?,?,?)",
                    GOAL_TABLE,
                    ID_PROPERTY,
                    GOAL_GETTER_ID_PROPERTY,
                    GOAL_GETTER_NAME_PROPERTY,
                    IS_OVERTIME_PROPERTY,
                    IS_OWN_GOAL_PROPERTY,
                    IS_PENALTY_PROPERTY,
                    MATCH_MINUTE_PROPERTY,
                    SCORE_TEAM_1_PROPERTY,
                    SCORE_TEAM_2_PROPERTY,
                    MATCH_ID_PROPERTY
            );

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, goal.getId());
            statement.setInt(2, goal.getGoalGetterId());
            statement.setString(3, goal.getGoalGetterName());
            statement.setBoolean(4, goal.isOverTime());
            statement.setBoolean(5, goal.isOwnGoal());
            statement.setBoolean(6, goal.isPenalty());
            statement.setInt(7, goal.getMinute());
            statement.setInt(8, goal.getScoreTeam1());
            statement.setInt(9, goal.getScoreTeam2());
            statement.setInt(10, goal.getMatchId());

            if (statement.execute()) {
                return new Result(true, String.format("Goal %d was saved successful", goal.getId()));
            } else {
                return new Result(false, "");
            }

        } catch (IllegalAccessException | InstantiationException | ClassNotFoundException | SQLException e) {
            return new Result(false, e.getMessage());
        }


    }

    @Override
    public Result delete(Entity entity) {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            connection = createConnection();

            String sql = String.format("DELETE FROM %s WHERE %s = %d", GOAL_TABLE, ID_PROPERTY, entity.getId());

            PreparedStatement statement = connection.prepareStatement(sql);

            if (statement.execute()) {
                return new Result(true, String.format("Goal %d erfolgreich geöscht", entity.getId()));
            } else {
                return new Result(false, "Unbekannter Fehler beim Löschen");
            }
        } catch (IllegalAccessException | InstantiationException | SQLException | ClassNotFoundException e) {
            return new Result(false, e.getMessage());
        }
    }

    @Override
    public boolean contains(Entity entity) {

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            connection = createConnection();

            String sql = String.format("SELECT * FROM %s WHERE %s = %d", GOAL_TABLE, ID_PROPERTY, entity.getId());

            PreparedStatement statement = connection.prepareStatement(sql);

            return statement.executeQuery().next();
        } catch (IllegalAccessException | InstantiationException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
}
