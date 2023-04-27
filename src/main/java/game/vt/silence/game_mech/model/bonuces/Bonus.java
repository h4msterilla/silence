package game.vt.silence.game_mech.model.bonuces;

import game.vt.silence.game_mech.model.values.VTValue;

public class Bonus {

    private VTValue targetValue;
    private int weight = 0;
    private String description;

    public Bonus(VTValue targetValue, int weight, String description){
        this.targetValue = targetValue;
        this.weight = weight;
        this.description = description;
    }

}
