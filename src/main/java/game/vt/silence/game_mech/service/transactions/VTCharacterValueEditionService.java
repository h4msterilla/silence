package game.vt.silence.game_mech.service.transactions;

import game.vt.silence.game_mech.model.VTCharacterValue;

import java.util.List;

public interface VTCharacterValueEditionService {

    void editValue(String charName, String valueName, String upDown);

    List<VTCharacterValue> getValues(String charName);

}
