package game.vt.silence.game_mech.filtration;

import game.vt.silence.game_mech.model.VTCharacterValue;

import java.util.List;

public interface VTCharacterValueRule {

    void doRule(List<VTCharacterValue> vtCharacterValues, VTCharacterValue vtCharacterValue, String upDown, VTCharacterValueRulesChainState state);

    int getOrder();
}
