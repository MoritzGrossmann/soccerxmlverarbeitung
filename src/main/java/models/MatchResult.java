package models;

import database.Entity;
import database.Result;
import database.WrongEntityTypeException;

public class MatchResult implements Entity {

    private int id;

    private int pointsTeam1;

    private int pointsTeam2;

    private String description;

    private String name;

    private int orderId;

    private int typeId;

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

    @Override
    public Result store()throws WrongEntityTypeException {
        return null;
    }

    @Override
    public boolean exist() {
        return false;
    }

    @Override
    public Result delete() {
        return null;
    }

    @Override
    public int getId() {
        return this.id;
    }
}
