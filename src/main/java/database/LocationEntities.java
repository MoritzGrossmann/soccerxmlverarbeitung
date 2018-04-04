package database;

import database.interfaces.Entity;
import models.Location;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LocationEntities extends DatabaseHelper {

    private static LocationEntities instance;

    private LocationEntities() {

    }

    public static LocationEntities getInstance() {
        if (instance == null) {
            instance = new LocationEntities();
        }

        return instance;
    }

    private static final String ID_PROPERTY = "LocationID";

    private static final String LOCATION_CITY_PROPERTY = "LocationCity";

    private static final String LOCAITONS_STADIUM_PROPERTY = "LocationStadium";

    @Override
    public Result push(Entity entity) throws WrongEntityTypeException {

        if (!(entity instanceof Location)) {
            throw new WrongEntityTypeException();
        }

        try {
            Location location = (Location)entity;

            connection = createConnection();

            String sql = String.format("INSERT INTO %s (%s,%s,%s) VALUES (?,?,?)",
                    LOCATION_TABLE,
                    ID_PROPERTY,
                    LOCATION_CITY_PROPERTY,
                    LOCAITONS_STADIUM_PROPERTY
            );

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, location.getId());
            statement.setString(2, location.getCity());
            statement.setString(3, location.getStadium());

            if (statement.execute()) {
                return new Result (true, String.format("Location %s %s was saved successful", location.getCity(), location.getStadium()));
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

            String sql = String.format("DELETE FROM %s WHERE %s = %d", LOCATION_TABLE, ID_PROPERTY, entity.getId());

            PreparedStatement statement = connection.prepareStatement(sql);

            if (statement.execute()) {
                return new Result(true, String.format("Location %d erfolgreich geöscht", entity.getId()));
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

            String sql = String.format("SELECT * FROM %s WHERE %s = %d", LOCATION_TABLE, ID_PROPERTY, entity.getId());

            PreparedStatement statement = connection.prepareStatement(sql);

            return statement.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
