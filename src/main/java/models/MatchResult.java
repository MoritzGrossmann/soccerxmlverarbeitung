package models;

import javax.persistence.*;

@Entity(name = "MatchResult")
@Table(name = "matchResult")
public class MatchResult {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "points_team_1")
    private int pointsTeam1;

    @Column(name = "points_team_2")
    private int pointsTeam2;

    @Column(name = "description")
    private String description;

    @Column(name = "name")
    private String name;

    @Column(name = "order_id")
    private int orderId;

    @Column(name = "type_id")
    private int typeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_id")
    private Match match;

    public void setId(int id) {
        this.id = id;
    }

    public int getPointsTeam1() {
        return pointsTeam1;
    }

    public void setPointsTeam1(int pointsTeam1) {
        this.pointsTeam1 = pointsTeam1;
    }

    public int getPointsTeam2() {
        return pointsTeam2;
    }

    public void setPointsTeam2(int pointsTeam2) {
        this.pointsTeam2 = pointsTeam2;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public int getId() {
        return id;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }
}
