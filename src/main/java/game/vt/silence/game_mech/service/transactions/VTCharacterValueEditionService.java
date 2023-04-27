package game.vt.silence.game_mech.service.transactions;

import game.vt.silence.game_mech.model.VTCharacter;
import game.vt.silence.game_mech.model.values.VTCharacterValue;

import java.util.List;

public interface VTCharacterValueEditionService {

    void editValue(String charName, String valueName, String upDown);

    void editValue4Vaadin(VTCharacter vtCharacter,List<VTCharacterValue> vtCharacterValueList, VTCharacterValue vtCharacterValue, String upDown);

    List<VTCharacterValue> getValues(String charName);

}
