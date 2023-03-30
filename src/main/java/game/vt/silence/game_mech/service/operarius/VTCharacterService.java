package game.vt.silence.game_mech.service.operarius;

import game.vt.silence.game_mech.model.VTCharacter;
import game.vt.silence.game_mech.model.VTCharacterValue;
import game.vt.silence.security.model.VTUser;

import java.util.List;

public interface VTCharacterService {

    VTCharacter getVTCharacterByName(String value_name);

    boolean existsVTCharacterByName(String value_name);

    void saveVTCharacter(VTCharacter vtCharacter);

    void createVTCharacter(String value_name);

    void addVTUser(VTCharacter vtCharacter, VTUser vtUser);

    void addVTCharacterValue(VTCharacter vtCharacter, VTCharacterValue vtCharacterValue);

    void addVTCharacterValue(VTCharacter vtCharacter, List<VTCharacterValue> vtCharacterValues);
}
