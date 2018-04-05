package database;

import database.interfaces.Entity;

public class PlayerEntities extends DatabaseHelper {

    private static PlayerEntities instance;

    private PlayerEntities() {

    }

    public static PlayerEntities getInstance() {
        if (instance == null) {
            instance = new PlayerEntities();
        }

        return instance;
    }

    @Override
    public Result push(Entity entity) throws WrongEntityTypeException {
        return null;
    }

    @Override
    public Result delete(Entity entity) {
        return null;
    }

    @Override
    public boolean contains(Entity entity) {
        return false;
    }
}
