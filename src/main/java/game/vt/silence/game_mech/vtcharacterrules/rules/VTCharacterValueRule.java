package game.vt.silence.game_mech.vtcharacterrules.rules;

import game.vt.silence.game_mech.model.VTCharacterValue;
import game.vt.silence.game_mech.model.VTValue;
import game.vt.silence.game_mech.vtcharacterrules.VTCharacterValueRulesChainStateHandler;

import java.util.Map;

public interface VTCharacterValueRule {

    void doRule(Map<VTValue, VTCharacterValue> vtValueMap, VTCharacterValue vtCharacterValue, int upDownArg, VTCharacterValueRulesChainStateHandler state);

    int getOrder();
}
