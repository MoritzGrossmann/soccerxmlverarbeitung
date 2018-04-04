package database;

import database.interfaces.Entity;
import models.Group;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GroupEntities extends DatabaseHelper {

    private static GroupEntities instance;

    private GroupEntities() {

    }

    public static GroupEntities getInstance() {
        if (instance == null) {
            instance = new GroupEntities();
        }
        return instance;
    }

    private static final String ID_PROPERTY = "GroupID";

    private static final String NAME_PROPERTY = "GroupName";

    private static final String ORDER_ID_PROPERTY = "GroupOrderID";

    @Override
    public Result push(Entity entity) throws WrongEntityTypeException {
        if (!(entity instanceof Group)){
            throw new WrongEntityTypeException();
        }

        Group group = (Group)entity;

        try {

            connection = createConnection();

            String sql = String.format("INSERT INTO %s (%s, %s, %s) VALUES (?,?,?)", GROUP_TABLE, ID_PROPERTY, NAME_PROPERTY, ORDER_ID_PROPERTY);

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, group.getId());
            statement.setString(2, group.getName());
            statement.setInt(3, group.getOrderId());

            if (statement.execute()) {
                return new Result(true, String.format("Group %s was saved successful", group.getName()));
            } else {
                return new Result(false, String.format("Unknown error while saving Group %s", group.getName()));
            }
        } catch (SQLException e) {
            return new Result(false, e.getMessage());
        }
    }

    @Override
    public Result delete(Entity entity) {
        try {

            connection = createConnection();

            String sql = String.format("DELETE FROM %s WHERE %s = %d", GROUP_TABLE, ID_PROPERTY, entity.getId());

            PreparedStatement statement = connection.prepareStatement(sql);

            if (statement.execute()) {
                return new Result(true, String.format("Group %d erfolgreich geöscht", entity.getId()));
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

            String sql = String.format("SELECT * FROM %s WHERE %s = %d", GROUP_TABLE, ID_PROPERTY, entity.getId());

            PreparedStatement statement = connection.prepareStatement(sql);

            return statement.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
