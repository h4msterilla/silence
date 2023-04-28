package game.vt.silence.game_mech.model.talents;

import game.vt.silence.game_mech.model.bonuces.Bonus;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class Talent {

    private String description;
    private List<Bonus> bonusList = new ArrayList<>();



}
