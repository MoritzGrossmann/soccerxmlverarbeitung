package models;

import database.Entity;
import database.Result;

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
    public Result store() {
        return null;
    }

    @Override
    public boolean exist() {
        return false;
    }

    @Override
    public Result delete() {
        return null;
    }

    @Override
    public int getId() {
        return this.id;
    }
}
