package game.vt.silence.game_mech.service;

import game.vt.silence.exceptions.CharacterNotFoundException;
import game.vt.silence.exceptions.NameOccupiedException;
import game.vt.silence.game_mech.model.VTCharacter;
import game.vt.silence.exceptions.VTCharacterValueWrongNameException;
import game.vt.silence.security.model.VTUser;

public interface VTCharacterService {

    VTCharacter getVTCharacterByName(String value_name) throws CharacterNotFoundException;

    boolean existsVTCharacterByName(String value_name);

    void saveVTCharacter(VTCharacter character);

    //void changeCharValueByName(String character, String valueName, String up_down) throws WrongCharacterValueNameException, CharacterNotFoundException;

    void createVTCharacter(String value_name) throws VTCharacterValueWrongNameException, NameOccupiedException;

    void addVTCharacter(VTUser user, VTCharacter character);

}
