package models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.List;

@Entity
public class Team  {

    @Id
    private int id;

    private String shortName;

    private String teamIconUrl;

    private String teamName;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Match.class)
    private List<Match> matches;

    public Team(int id, String shortName, String teamIconUrl, String teamName) {
        this.id = id;
        this.shortName = shortName;
        this.teamIconUrl = teamIconUrl;
        this.teamName = teamName;
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
