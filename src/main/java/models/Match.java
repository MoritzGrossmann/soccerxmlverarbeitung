package models;

import database.MatchEntities;
import database.Result;
import database.WrongEntityTypeException;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
public class Match  {

    @Id
    private int id;

    private Date lastUpdateTime;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = League.class)
    @JoinColumn(name = "id")
    private League league;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = Match.class)
    @JoinColumn(name = "id")
    private Location location;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = Group.class)
    @JoinColumn(name = "id")
    private Group group;

    private Date dateTime;

    private Date dateTimeUTC;

    private boolean finished;

    private int numberOfViewers;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = Team.class)
    @JoinColumn(name = "id")
    private Team team1;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = Team.class)
    @JoinColumn(name = "id")
    private Team team2;

    private String timezone;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = MatchResult.class)
    private List<MatchResult> matchResults;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Goal.class)
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

    public int getId() {
        return id;
    }
}
