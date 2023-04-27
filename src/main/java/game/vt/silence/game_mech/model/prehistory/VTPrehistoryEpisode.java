package game.vt.silence.game_mech.model.prehistory;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

//@Entity
@Getter
@Setter
public class VTPrehistoryEpisode {

    int firstDice;
    String firstDiceDescription;
    int secondDice;
    String secondDiceDescription;
    String episodeDescription;
    String bonusesDescription;

    String defaultBonus;
    String addBonus;

}
