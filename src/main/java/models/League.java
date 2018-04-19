package models;

import javax.persistence.*;
import java.util.List;

@Entity(name = "League")
@Table(name = "league")
public class League  {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name = "";

    public League(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "league",cascade = CascadeType.ALL)
    private List<Match> matches;

    public League() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
