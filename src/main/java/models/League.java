package models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.List;

@Entity
public class League  {

    @Id
    private int id;

    private String name = "";

    public League(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Match.class)
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
}
