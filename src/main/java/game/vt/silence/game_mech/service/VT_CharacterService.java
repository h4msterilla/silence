package game.vt.silence.game_mech.service;

import game.vt.silence.game_mech.model.VT_Character;
import game.vt.silence.game_mech.model.WrongCharacterValueNameException;

public interface VT_CharacterService {

    VT_Character getVT_CharacterByName(String name);

    void saveCharacter(VT_Character character);

    void changeCharValueByName(VT_Character character, String valueName, String up_down) throws WrongCharacterValueNameException;

    void createCharacter(String value_name);

}
