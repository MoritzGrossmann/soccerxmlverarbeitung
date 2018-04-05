package models;

import database.PlayerEntities;
import database.Result;
import database.WrongEntityTypeException;
import database.interfaces.Entity;

import java.util.Date;

public class Player implements Entity {

    private int teamId;

    private int trikotNr;

    private String name;

    private Date birthDate;

    private int minutes;

    private int goals;

    private int yellowCards;

    private int yellowRedCards;

    private int redCards;

    public Player() {
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public int getTrikotNr() {
        return trikotNr;
    }

    public void setTrikotNr(int trikotNr) {
        this.trikotNr = trikotNr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public int getYellowCards() {
        return yellowCards;
    }

    public void setYellowCards(int yellowCards) {
        this.yellowCards = yellowCards;
    }

    public int getYellowRedCards() {
        return yellowRedCards;
    }

    public void setYellowRedCards(int yellowRedCards) {
        this.yellowRedCards = yellowRedCards;
    }

    public int getRedCards() {
        return redCards;
    }

    public void setRedCards(int redCards) {
        this.redCards = redCards;
    }

    @Override
    public Result store() throws WrongEntityTypeException {
        return PlayerEntities.getInstance().push(this);
    }

    @Override
    public boolean exist() {
        return PlayerEntities.getInstance().contains(this);
    }

    @Override
    public Result delete() {
        return PlayerEntities.getInstance().delete(this);
    }

    @Override
    public int getId() {
        return 0;
    }
}
