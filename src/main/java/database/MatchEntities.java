package database;

import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;
import models.Match;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MatchEntities extends DatabaseHelper{

    private static MatchEntities instance;

    private MatchEntities() {

    }

    public static MatchEntities getInstance() {
        if (instance == null) {
            instance = new MatchEntities();
        }
        return instance;
    }

    private static final String ID_PROPERTY = "MatchID";

    private static final String LAST_UPDATE_TIME_PROPERTY = "LastUpdateTime";

    private static final String LEAGUE_ID_PROPERTY = "LeagueID";

    private static final String LOCATION_ID_PROPERTY = "LocationID";

    private static final String MATCH_DATE_TIME_PROPERTY = "MatchDateTime";

    private static final String MATCH_DATE_TIME_UTC_PROPERTY = "MatchDateTimeUTC";

    private static final String MATCH_IS_FINISHED_PROPERTY = "MatchIsFinished";

    private static final String NUMBER_OF_VIEWERS_PROPERTY = "NumberOfViewers";

    private static final String TEAM_1_ID_PROPERTY = "Team1ID";

    private static final String TEAM_2_ID_PROPERTY ="Team2ID";

    @Override
    public boolean contains(Entity entity) {

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            connection = createConnection();

            String sql = String.format("SELECT * FROM %s WHERE %s = %d", MATCH_TABLE, ID_PROPERTY, entity.getId());

            PreparedStatement statement = connection.prepareStatement(sql);

            return statement.executeQuery().next();
        } catch (IllegalAccessException | InstantiationException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Result push(Entity entity) throws WrongEntityTypeException{
        try {

            if (!(entity instanceof Match)) {
                throw new WrongEntityTypeException();
            }

            Match match = (Match) entity;

            if (!match.getLeague().exist()) {
                match.getLeague().store();
            }

            if (!match.getLocation().exist()) {
                match.getLocation().store();
            }

            if (!match.getGroup().exist()){
                match.getGroup().store();
            }

            Class.forName("com.mysql.jdbc.Driver").newInstance();

            connection = createConnection();

            String sql = String.format("INSERT INTO %s (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s) values (?,?,?,?,?,?,?,?,?,?)",
                    MATCH_TABLE,
                    ID_PROPERTY,
                    LAST_UPDATE_TIME_PROPERTY,
                    LEAGUE_ID_PROPERTY,
                    LOCATION_ID_PROPERTY,
                    MATCH_DATE_TIME_PROPERTY,
                    MATCH_DATE_TIME_UTC_PROPERTY,
                    MATCH_IS_FINISHED_PROPERTY,
                    NUMBER_OF_VIEWERS_PROPERTY,
                    TEAM_1_ID_PROPERTY,
                    TEAM_2_ID_PROPERTY
            );

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, match.getId());
            statement.setDate(2, match.getLastUpdateTime());
            statement.setInt(3, match.getLeague().getId());
            statement.setInt(4, match.getLocation().getId());
            statement.setDate(5, match.getDateTime());
            statement.setDate(6, match.getDateTimeUTC());
            statement.setBoolean(7, match.isFinished());
            statement.setInt(8, match.getNumberOfViewers());
            statement.setInt(9, match.getTeam1().getId());
            statement.setInt(10, match.getTeam2().getId());

            if (statement.execute()) {

                match.getGoals().forEach(goal -> {
                    try {
                        goal.store();
                    } catch (WrongEntityTypeException e) {
                        e.printStackTrace();
                    }
                });

                match.getMatchResults().forEach(matchResult -> {
                    try {
                        matchResult.store();
                    } catch (WrongEntityTypeException e) {
                        e.printStackTrace();
                    }
                });

                return new Result(true, String.format("Match %s was saved succesful", match.getId()));
            } else {
                return new Result(false, "Unbekannter Fehler beim Speichern");
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

            String sql = String.format("DELETE FROM %s WHERE %s = %d", MATCH_TABLE, ID_PROPERTY, entity.getId());

            PreparedStatement statement = connection.prepareStatement(sql);

            if (statement.execute()) {
                return new Result(true, String.format("Match %d erfolgreich geöscht", entity.getId()));
            } else {
                return new Result(false, "Unbekannter Fehler beim Löschen");
            }
        } catch (IllegalAccessException | InstantiationException | SQLException | ClassNotFoundException e) {
            return new Result(false, e.getMessage());
        }
    }
}
