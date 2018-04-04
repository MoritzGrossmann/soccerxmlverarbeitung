package models;

import database.interfaces.Entity;
import database.LeagueEntities;
import database.Result;
import database.WrongEntityTypeException;

public class League implements Entity {

    private int id;

    private String name = "";

    public League(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public League() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Result store() throws WrongEntityTypeException {
        return LeagueEntities.getInstance().push(this);
    }

    @Override
    public boolean exist() {
        return LeagueEntities.getInstance().contains(this);
    }

    @Override
    public Result delete() {
        return LeagueEntities.getInstance().delete(this);
    }

    @Override
    public int getId() {
        return this.id;
    }
}
