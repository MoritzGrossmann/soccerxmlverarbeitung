package models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.List;

@Entity
public class Location {

    @Id
    private int id;

    private String city;

    private String stadium;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Match.class)
    private List<Match> matches;

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

    public int getId() {
        return this.id;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }
}
