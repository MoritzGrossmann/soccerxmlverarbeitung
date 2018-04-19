package models;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity(name = "Match")
@Table(name = "match")
public class Match  {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "last_update_times")
    private Date lastUpdateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "league_id")
    private League league;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "locaton_id")
    private Location location;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;

    @Column(name = "datetime")
    private Date dateTime;

    @Column(name = "datetime_utc")
    private Date dateTimeUTC;

    @Column(name = "finished")
    private boolean finished;

    @Column(name = "number_of_viewers")
    private int numberOfViewers;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_1_id")
    private Team team1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_2_id")
    private Team team2;

    @Column(name = "timezone")
    private String timezone;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "match", cascade = CascadeType.ALL)
    private List<MatchResult> matchResults;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "match", cascade = CascadeType.ALL)
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
