package game.vt.silence.game_mech.model.bonuces;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BonusPack {

    private List<Bonus> bonusList = new ArrayList<>();

    public BonusPack(Bonus... bonuses){
        bonusList.addAll(Arrays.stream(bonuses).toList());
    }

}
