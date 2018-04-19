package models;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "Player")
@Table(name = "player")
public class Player {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "team_id")
    private int teamId;

    @Column(name = "trikot_nr")
    private int trikotNr;

    @Column(name = "name")
    private String name;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "minutes")
    private int minutes;

    @Column(name = "goals")
    private int goals;

    @Column(name = "yellow_cards")
    private int yellowCards;

    @Column(name = "yellow_red_cards")
    private int yellowRedCards;

    @Column(name = "red_cards")
    private int redCards;

    public Player() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    public String toString() {
        return this.teamId + " " + this.trikotNr + " " + this.name;
    }
}
