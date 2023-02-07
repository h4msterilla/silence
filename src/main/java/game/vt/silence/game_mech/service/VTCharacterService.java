package game.vt.silence.game_mech.service;

import game.vt.silence.exceptions.CharacterNotFoundException;
import game.vt.silence.exceptions.NameOccupiedException;
import game.vt.silence.game_mech.model.VTCharacter;
import game.vt.silence.exceptions.WrongCharacterValueNameException;
import game.vt.silence.security.model.VTUser;

public interface VTCharacterService {

    VTCharacter getVTCharacterByName(String value_name) throws CharacterNotFoundException;

    boolean existsVTCharacterByName(String value_name);

    void saveCharacter(VTCharacter character);

    void changeCharValueByName(String character, String valueName, String up_down) throws WrongCharacterValueNameException, CharacterNotFoundException;

    void createCharacter(String value_name) throws WrongCharacterValueNameException, NameOccupiedException;

    void addVTCharacter(VTUser user, VTCharacter character);

}
