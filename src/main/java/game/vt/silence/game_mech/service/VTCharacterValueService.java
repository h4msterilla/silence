package game.vt.silence.game_mech.service;

import game.vt.silence.game_mech.model.VTCharacter;
import game.vt.silence.game_mech.model.VTCharacterValue;

public interface VTCharacterValueService {

    void save(VTCharacterValue vtCharacterValue);

    void edit(String vtCharname, String vtCharacterValue, String upDown);

}
