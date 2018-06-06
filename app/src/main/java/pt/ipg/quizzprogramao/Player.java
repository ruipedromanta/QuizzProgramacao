package pt.ipg.quizzprogramao;

/**
 * Created by ruima on 06/06/2018.
 */

public class Player {
    private int id;
    private String name;
    private int best_score;

    public int getId() {
        return id;
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

    public int getBest_score() {
        return best_score;
    }

    public void setBest_score(int best_score) {
        this.best_score = best_score;
    }
}
