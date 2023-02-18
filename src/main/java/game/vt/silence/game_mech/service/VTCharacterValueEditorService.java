package game.vt.silence.game_mech.service;

import game.vt.silence.game_mech.model.VTCharacterValue;

import java.util.List;

public interface VTCharacterValueEditorService {

    void editValue(String charName, String valueName, String upDown);

    List<VTCharacterValue> getValues(String charName);

}
