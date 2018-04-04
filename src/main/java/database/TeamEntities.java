package database;

import database.interfaces.Entity;
import models.Team;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TeamEntities extends DatabaseHelper {

    private static TeamEntities instance;

    private TeamEntities() {

    }

    public static TeamEntities getInstance() {
        if (instance == null) {
            instance = new TeamEntities();
        }

        return instance;
    }

    private static final String ID_PROPERTY = "TeamID";

    private static final String SHORT_NAME_PROPERTY = "ShortName";

    private static final String ICON_URL_PROPERTY = "TeamIconUrl";

    private static final String NAME_PROPERTY = "TeamName";

    @Override
    public Result push(Entity entity) throws WrongEntityTypeException {

        try {

            if(!(entity instanceof Team)){
                throw new WrongEntityTypeException();
            }

            Team team = (Team)entity;

            connection = createConnection();

            String sql = String.format("INSERT INTO %s (%s, %s, %s, %s) values (?,?,?,?)", TEAM_TABLE, ID_PROPERTY, SHORT_NAME_PROPERTY, ICON_URL_PROPERTY, NAME_PROPERTY);

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, team.getId());
            statement.setString(2, team.getShortName());
            statement.setString(3, team.getTeamIconUrl());
            statement.setString(4, team.getTeamName());

            statement.execute();

            statement.close();

            return new Result(true, String.format("Team %s was saved successful", team.getTeamName()));

        } catch (SQLException e) {
            return new Result(false, e.getMessage());
        }
    }

    @Override
    public Result delete(Entity entity) {
        try {
            connection = createConnection();

            String sql = String.format("DELETE FROM %s WHERE %s = %d", TEAM_TABLE, ID_PROPERTY, entity.getId());

            PreparedStatement statement = connection.prepareStatement(sql);

            if (statement.execute()) {
                return new Result(true, String.format("Team %d erfolgreich geöscht", entity.getId()));
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

            String sql = String.format("SELECT * FROM %s WHERE %s = %d", TEAM_TABLE, ID_PROPERTY, entity.getId());

            PreparedStatement statement = connection.prepareStatement(sql);

            return statement.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
