package models;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Loaction")
@Table(name = "location")
public class Location {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "city")
    private String city;

    @Column(name = "stadium")
    private String stadium;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "location", cascade = CascadeType.ALL)
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
