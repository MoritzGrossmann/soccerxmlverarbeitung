package database;

import database.interfaces.Entity;
import models.Match;
import models.MatchResult;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MatchResultEntities extends DatabaseHelper {

    private static MatchResultEntities instance;

    private MatchResultEntities() {

    }

    public static MatchResultEntities getInstance() {
        if (instance == null) {
            instance = new MatchResultEntities();
        }

        return instance;
    }

    private static final String POINTS_TEAM_1_PROPERTY = "PointsTeam1";

    private static final String POINTS_TEAM_2_PROPERTY = "PointsTeam2";

    private static final String RESULT_DESCRIPTION_PROPERTY = "ResultDescription";

    private static final String ID_PROPERTY = "ResultID";

    private static final String RESULT_NAME_PROPERTY = "ResultName";

    private static final String RESULT_ORDER_ID_PROPERTY = "ResultOrderID";

    private static final String RESULT_TYPE_ID_PROPERTY = "ResultTypeID";

    private static final String MATCH_ID_PROPERTY = "MatchID";

    @Override
    public Result push(Entity entity) throws WrongEntityTypeException {
        if (!(entity instanceof MatchResult)) {
            throw new WrongEntityTypeException();
        }

        try {
            MatchResult matchResult = (MatchResult) entity;

            connection = createConnection();

            String sql = String.format("INSERT INTO %s (%s,%s,%s,%s,%s,%s,%s,%s) VALUES (?,?,?,?,?,?,?,?)",
                    MATCH_RESULT_TABLE,
                    ID_PROPERTY,
                    MATCH_ID_PROPERTY,
                    RESULT_ORDER_ID_PROPERTY,
                    RESULT_TYPE_ID_PROPERTY,
                    RESULT_NAME_PROPERTY,
                    RESULT_DESCRIPTION_PROPERTY,
                    POINTS_TEAM_1_PROPERTY,
                    POINTS_TEAM_2_PROPERTY);

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, matchResult.getId());
            statement.setInt(2, matchResult.getMatchId());
            statement.setInt(3, matchResult.getOrderId());
            statement.setInt(4, matchResult.getTypeId());
            statement.setString(5, matchResult.getName());
            statement.setString(6, matchResult.getDescription());
            statement.setInt(7, matchResult.getPointsTeam1());
            statement.setInt(8, matchResult.getPointsTeam2());

            if (statement.execute()) {
                return new Result(true, String.format("Match-Result %d was saved successful", matchResult.getId()));
            } else {
                return new Result(false, "");
            }
        } catch (SQLException e) {
            return new Result(false, e.getMessage());
        }
    }

    @Override
    public Result delete(Entity entity) {
        try {
            connection = createConnection();

            String sql = String.format("DELETE FROM %s WHERE %s = %d", MATCH_RESULT_TABLE, ID_PROPERTY, entity.getId());

            PreparedStatement statement = connection.prepareStatement(sql);

            if (statement.execute()) {
                return new Result(true, String.format("Match-Result %d erfolgreich geöscht", entity.getId()));
            } else {
                return new Result(false, "Unbekannter Fehler beim Löschen");
            }
        } catch (SQLException e) {
            return new Result(false, e.getMessage());
        }
    }

    @Override
    public boolean contains(Entity entity) {

        try {
            connection = createConnection();

            String sql = String.format("SELECT * FROM %s WHERE %s = %d", MATCH_RESULT_TABLE, ID_PROPERTY, entity.getId());

            PreparedStatement statement = connection.prepareStatement(sql);

            return statement.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
