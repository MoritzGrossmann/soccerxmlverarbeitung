package models;

import database.LocationEntities;
import database.Result;
import database.WrongEntityTypeException;
import database.interfaces.Entity;

public class Location implements Entity {

    private int id;

    private String city;

    private String stadium;

    public Location(int id, String city, String stadium) {
        this.id = id;
        this.city = city;
        this.stadium = stadium;
    }

    public Location() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStadium() {
        return stadium;
    }

    public void setStadium(String stadium) {
        this.stadium = stadium;
    }

    @Override
    public Result store() throws WrongEntityTypeException{
        return LocationEntities.getInstance().push(this);
    }

    @Override
    public boolean exist() {
        return LocationEntities.getInstance().contains(this);
    }

    @Override
    public Result delete() {
        return LocationEntities.getInstance().delete(this);
    }

    @Override
    public int getId() {
        return this.id;
    }
}
