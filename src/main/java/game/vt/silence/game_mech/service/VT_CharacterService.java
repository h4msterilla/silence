package game.vt.silence.game_mech.service;

import game.vt.silence.game_mech.model.CharacterNotFoundException;
import game.vt.silence.game_mech.model.NameOccupiedException;
import game.vt.silence.game_mech.model.VT_Character;
import game.vt.silence.game_mech.model.WrongCharacterValueNameException;

public interface VT_CharacterService {

    VT_Character getVT_CharacterByName(String value_name) throws CharacterNotFoundException;

    boolean existsVT_CharacterByName(String value_name);

    void saveCharacter(VT_Character character);

    void changeCharValueByName(String character, String valueName, String up_down) throws WrongCharacterValueNameException, CharacterNotFoundException;

    void createCharacter(String value_name) throws WrongCharacterValueNameException, NameOccupiedException;

}
