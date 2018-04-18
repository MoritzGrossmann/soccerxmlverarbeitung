package models;

import database.GroupEntities;
import database.Result;
import database.WrongEntityTypeException;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.List;

@Entity
public class Group {

    @Id
    private int id;

    private String name;

    private int orderId;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Match.class)
    private List<Match> matches;

    public Group() {
    }

    public Group(int id, String name, int orderId) {
        this.id = id;
        this.name = name;
        this.orderId = orderId;
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

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getId() {
        return id;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }
}
