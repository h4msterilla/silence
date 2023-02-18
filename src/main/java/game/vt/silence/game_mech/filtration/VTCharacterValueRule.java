package game.vt.silence.game_mech.filtration;

import game.vt.silence.game_mech.model.VTCharacterValue;

import java.util.List;
import java.util.Map;

public interface VTCharacterValueRule {

    void doRule(Map<String, VTCharacterValue> vtValueMap, VTCharacterValue vtCharacterValue, String upDown, VTCharacterValueRulesChainState state);

    int getOrder();
}
