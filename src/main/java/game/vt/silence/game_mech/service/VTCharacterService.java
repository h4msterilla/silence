package game.vt.silence.game_mech.service;

import game.vt.silence.exceptions.VTCharacterNotFoundException;
import game.vt.silence.exceptions.VTCharacterNameOccupiedException;
import game.vt.silence.game_mech.model.VTCharacter;
import game.vt.silence.exceptions.VTCharacterValueNotFoundException;
import game.vt.silence.security.model.VTUser;

public interface VTCharacterService {

    VTCharacter getVTCharacterByName(String value_name) throws VTCharacterNotFoundException;

    boolean existsVTCharacterByName(String value_name);

    void saveVTCharacter(VTCharacter character);

    //void changeCharValueByName(String character, String valueName, String up_down) throws WrongCharacterValueNameException, CharacterNotFoundException;

    void createVTCharacter(String value_name) throws VTCharacterValueNotFoundException, VTCharacterNameOccupiedException;

    void addVTCharacter(VTUser user, VTCharacter character);

}
