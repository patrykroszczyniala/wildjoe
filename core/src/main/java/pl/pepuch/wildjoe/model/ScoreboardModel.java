package pl.pepuch.wildjoe.model;

public class ScoreboardModel extends StaticModel {

    private int points;
    private int lives;
    private int level;

    public ScoreboardModel() {
        super();
        level = 1;
        points = 0;
        lives = 3;
    }

    public int points() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int lives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int level() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
