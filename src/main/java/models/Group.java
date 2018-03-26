package models;

import database.Entity;
import database.GroupEntities;
import database.Result;
import database.WrongEntityTypeException;

public class Group implements Entity {

    private int id;

    private String name;

    private int orderId;

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

    @Override
    public Result store() throws WrongEntityTypeException {
        return GroupEntities.getInstance().push(this);
    }

    @Override
    public boolean exist() {
        return GroupEntities.getInstance().contains(this);
    }

    @Override
    public Result delete() {
        return GroupEntities.getInstance().delete(this);
    }

    @Override
    public int getId() {
        return this.id;
    }
}
