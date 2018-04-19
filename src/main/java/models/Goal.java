package models;

import javax.persistence.*;

@Entity(name = "Goal")
@Table(name="goal")
public class Goal {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "goalgetter_id")
    private int goalGetterId;

    @Column(name = "goalgetter_name")
    private String goalGetterName;

    @Column(name = "overtime")
    private boolean overTime;

    @Column(name = "own_goal")
    private boolean ownGoal;

    @Column(name = "penalty")
    private boolean penalty;

    @Column(name = "minute")
    private int minute;

    @Column(name = "score_team_1")
    private int scoreTeam1;

    @Column(name = "score_team_2")
    private int scoreTeam2;

    @Column(name = "comment")
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_id")
    private Match match;

    public Goal() {

    }

    public Goal(int id, int goalGetterId, String goalGetterName, int matchId, boolean overTime, boolean ownGoal, boolean penalty, int minute, int scoreTeam1, int scoreTeam2) {
        this.id = id;
        this.goalGetterId = goalGetterId;
        this.goalGetterName = goalGetterName;
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
