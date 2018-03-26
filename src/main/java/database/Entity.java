package database;

public interface Entity {

    Result store() throws WrongEntityTypeException;

    boolean exist();

    Result delete();

    int getId();
}
