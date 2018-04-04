package models;

import database.interfaces.Entity;
import database.MatchEntities;
import database.Result;
import database.WrongEntityTypeException;

import java.sql.Date;
import java.util.List;

public class Match implements Entity {

    private int id;

    private Date lastUpdateTime;

    private League league;

    private Location location;

    private Group group;

    private Date dateTime;

    private Date dateTimeUTC;

    private boolean finished;

    private int numberOfViewers;

    private Team team1;

    private Team team2;

    private String timezone;

    private List<MatchResult> matchResults;

    private List<Goal> goals;

    public Match() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Date getDateTimeUTC() {
        return dateTimeUTC;
    }

    public void setDateTimeUTC(Date dateTimeUTC) {
        this.dateTimeUTC = dateTimeUTC;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public int getNumberOfViewers() {
        return numberOfViewers;
    }

    public void setNumberOfViewers(int numberOfViewers) {
        this.numberOfViewers = numberOfViewers;
    }

    public Team getTeam1() {
        return team1;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public List<MatchResult> getMatchResults() {
        return matchResults;
    }

    public void setMatchResults(List<MatchResult> matchResults) {
        this.matchResults = matchResults;
    }

    public List<Goal> getGoals() {
        return goals;
    }

    public void setGoals(List<Goal> goals) {
        this.goals = goals;
    }

    @Override
    public Result store() throws WrongEntityTypeException {
        return MatchEntities.getInstance().push(this);
    }

    @Override
    public boolean exist() {
        return MatchEntities.getInstance().contains(this);
    }

    @Override
    public Result delete() {
        return MatchEntities.getInstance().delete(this);
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return String.format("%d %s %s %s vs. %s", this.id, this.league.getName(), this.group.getName(), this.team1.getTeamName(), this.team2.getTeamName());
    }
}
