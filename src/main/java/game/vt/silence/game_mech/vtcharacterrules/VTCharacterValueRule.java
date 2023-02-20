package game.vt.silence.game_mech.vtcharacterrules;

import game.vt.silence.game_mech.model.VTCharacterValue;

import java.util.Map;

public interface VTCharacterValueRule {

    void doRule(Map<String, VTCharacterValue> vtValueMap, VTCharacterValue vtCharacterValue, int upDownArg, VTCharacterValueRulesChainState state);

    int getOrder();
}
