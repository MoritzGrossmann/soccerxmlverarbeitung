package database;

public class MatchResultEntities extends DatabaseHelper {
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
