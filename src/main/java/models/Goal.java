package models;

import database.Entity;
import database.GoalEntities;
import database.Result;
import database.WrongEntityTypeException;

public class Goal implements Entity {

    private int id;

    private int goalGetterId;

    private String goalGetterName;

    private int matchId;

    private boolean overTime;

    private boolean ownGoal;

    private boolean penalty;

    private int minute;

    private int scoreTeam1;

    private int scoreTeam2;

    private String comment;

    public Goal() {

    }

    public Goal(int id, int goalGetterId, String goalGetterName, int matchId, boolean overTime, boolean ownGoal, boolean penalty, int minute, int scoreTeam1, int scoreTeam2) {
        this.id = id;
        this.goalGetterId = goalGetterId;
        this.goalGetterName = goalGetterName;
        this.matchId = matchId;
        this.overTime = overTime;
        this.ownGoal = ownGoal;
        this.penalty = penalty;
        this.minute = minute;
        this.scoreTeam1 = scoreTeam1;
        this.scoreTeam2 = scoreTeam2;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGoalGetterId() {
        return goalGetterId;
    }

    public void setGoalGetterId(int goalGetterId) {
        this.goalGetterId = goalGetterId;
    }

    public String getGoalGetterName() {
        return goalGetterName;
    }

    public void setGoalGetterName(String goalGetterName) {
        this.goalGetterName = goalGetterName;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public boolean isOverTime() {
        return overTime;
    }

    public void setOverTime(boolean overTime) {
        this.overTime = overTime;
    }

    public boolean isOwnGoal() {
        return ownGoal;
    }

    public void setOwnGoal(boolean ownGoal) {
        this.ownGoal = ownGoal;
    }

    public boolean isPenalty() {
        return penalty;
    }

    public void setPenalty(boolean penalty) {
        this.penalty = penalty;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getScoreTeam1() {
        return scoreTeam1;
    }

    public void setScoreTeam1(int scoreTeam1) {
        this.scoreTeam1 = scoreTeam1;
    }

    public int getScoreTeam2() {
        return scoreTeam2;
    }

    public void setScoreTeam2(int scoreTeam2) {
        this.scoreTeam2 = scoreTeam2;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public Result store() throws WrongEntityTypeException {
        return GoalEntities.getInstance().push(this);
    }

    @Override
    public boolean exist() {
        return GoalEntities.getInstance().contains(this);
    }

    @Override
    public Result delete() {
        return GoalEntities.getInstance().delete(this);
    }

    @Override
    public int getId() {
        return this.id;
    }
}
