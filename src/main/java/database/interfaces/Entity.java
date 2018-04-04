package database.interfaces;

import database.Result;
import database.WrongEntityTypeException;

public interface Entity {

    Result store() throws WrongEntityTypeException;

    boolean exist();

    Result delete();

    int getId();
}
