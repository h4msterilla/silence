package game.vt.silence.game_mech.model;

import game.vt.silence.game_mech.model.bonuces.Bonus;
import game.vt.silence.game_mech.model.bonuces.BonusPack;
import game.vt.silence.game_mech.model.values.VTValue;

public enum Phenotype {
    CAPITALIST(//Столичник
            new BonusPack(new Bonus(VTValue.SKILL_ENDURANCE_SENSE_OF_TOWN, 1, "capitalist bonus")),
            new BonusPack(new Bonus(VTValue.SKILL_ENDURANCE_ADAPTABILITY, 1, "capitalist bonus"))
    ),
    STEPPEDWELLER(//Степняк

    ),
    ADAPTDWELLER(//Степняк-адаптант

    ),
    HALF_BLOOD(//Полукровка

    ),
    ELDER(//Древний

    ),
    HIGHLANDER(//Горняк

    ),
    SLITHER(//Серпарь

    ),
    COLLECTOR(//Коллектор

    ),
    MENTALIST(//Ментал

    ),
    PIPER(//Трубач

    ),
    SOLID(//Солид

    );

    Phenotype(BonusPack... packs) {

    }
}
