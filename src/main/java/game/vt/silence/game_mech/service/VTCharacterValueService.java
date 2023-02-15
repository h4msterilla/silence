package game.vt.silence.game_mech.service;

import game.vt.silence.game_mech.model.VTCharacter;
import game.vt.silence.game_mech.model.VTCharacterValue;

import java.util.List;

public interface VTCharacterValueService {

    void save(VTCharacterValue vtCharacterValue);

    void save(List<VTCharacterValue> vtCharacterValues);

    void setVTCharacter(VTCharacterValue vtCharacterValue,VTCharacter vtCharacter);

    void setVTCharacter(List<VTCharacterValue> vtCharacterValues, VTCharacter vtCharacter);

    List<VTCharacterValue> getDefaultVTCharacterValuesList();

    List<VTCharacterValue> getVTCharacterValuesByVTCharacter(VTCharacter vtCharacter);

}
