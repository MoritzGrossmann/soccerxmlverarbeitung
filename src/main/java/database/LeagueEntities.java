package database;

import database.interfaces.Entity;
import models.League;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LeagueEntities extends DatabaseHelper {

    private static LeagueEntities instance;

    private LeagueEntities() {

    }

    public static LeagueEntities getInstance()
    {
        if (instance == null) {
            instance = new LeagueEntities();
        }

        return instance;
    }

    private static final String ID_PROPERTY = "LeagueID";

    private static final String LEAGUE_NAME_PROPERTY = "LeagueName";

    @Override
    public Result push(Entity entity) throws WrongEntityTypeException {
        if(!(entity instanceof League)){
            throw new WrongEntityTypeException();
        }

        try {
            League league = (League) entity;

            connection = createConnection();

            String sql = String.format("INSERT INTO %s (%s,%s) VALUES (?,?)", LEAGUE_TABLE, ID_PROPERTY, LEAGUE_NAME_PROPERTY);

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, league.getId());
            statement.setString(2, league.getName());

            if (statement.execute()) {
                return new Result(true, String.format("League %s was saved successful", league.getName()));
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

            String sql = String.format("DELETE FROM %s WHERE %s = %d", LEAGUE_TABLE, ID_PROPERTY, entity.getId());

            PreparedStatement statement = connection.prepareStatement(sql);

            if (statement.execute()) {
                return new Result(true, String.format("League %d erfolgreich geöscht", entity.getId()));
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

            String sql = String.format("SELECT * FROM %s WHERE %s = %d", LEAGUE_TABLE, ID_PROPERTY, entity.getId());

            PreparedStatement statement = connection.prepareStatement(sql);

            return statement.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
