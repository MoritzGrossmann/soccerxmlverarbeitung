package models;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Group")
@Table(name = "group")
public class Group {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "order_id")
    private int orderId;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "group", cascade = CascadeType.ALL)
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
