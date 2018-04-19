package models;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Team")
@Table(name = "team")
public class Team  {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "short_name")
    private String shortName;

    @Column(name = "icon_url")
    private String teamIconUrl;

    @Column(name = "name")
    private String teamName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "team", cascade = CascadeType.ALL)
    private List<Match> matches;

    public Team(int id, String shortName, String teamIconUrl, String teamName) {
        this.id = id;
        this.shortName = shortName;
        this.teamIconUrl = teamIconUrl;
        this.teamName = teamName;
    }

    public Team() {

    }

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

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }
}
