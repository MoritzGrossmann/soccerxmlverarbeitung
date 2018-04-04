package models;

import database.interfaces.Entity;
import database.Result;
import database.TeamEntities;
import database.WrongEntityTypeException;

public class Team implements Entity {

    private int id;

    private String shortName;

    private String teamIconUrl;

    private String teamName;

    public Team(int id, String shortName, String teamIconUrl, String teamName) {
        this.id = id;
        this.shortName = shortName;
        this.teamIconUrl = teamIconUrl;
        this.teamName = teamName;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getTeamIconUrl() {
        return teamIconUrl;
    }

    public void setTeamIconUrl(String teamIconUrl) {
        this.teamIconUrl = teamIconUrl;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    @Override
    public Result store() throws WrongEntityTypeException {
        return TeamEntities.getInstance().push(this);
    }

    @Override
    public boolean exist() {
        return TeamEntities.getInstance().contains(this);
    }

    @Override
    public Result delete() {
        return TeamEntities.getInstance().delete(this);
    }
}
