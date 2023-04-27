package game.vt.silence.game_mech.vtcharacterrules.rules;

import game.vt.silence.exceptions.VTCharacterValueBreakRuleException;
import game.vt.silence.game_mech.model.values.VTValue;
import game.vt.silence.game_mech.model.values.VTValueTag;
import game.vt.silence.game_mech.vtcharacterrules.VTCharacterValueRulesChainStateHandler;
import game.vt.silence.game_mech.vtcharacterrules.VTCharacterValueRulesChainState;
import game.vt.silence.game_mech.model.values.VTCharacterValue;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ConnectionAndKnowledgeRule implements VTCharacterValueRule {
    @Override
    public void doRule(Map<VTValue, VTCharacterValue> vtValueMap, VTCharacterValue vtCharacterValue, int upDownArg, VTCharacterValueRulesChainStateHandler state) {

        //if (!(vtCharacterValue.getType().equalsIgnoreCase("connections")
        //        || vtCharacterValue.getType().equalsIgnoreCase("knowledge"))) return;
        if(!(vtCharacterValue.containsTag(VTValueTag.CONNECTIONS)
                || vtCharacterValue.containsTag(VTValueTag.KNOWLEDGE))) return;

        if (vtCharacterValue.getValue() + upDownArg < 0)
            throw new VTCharacterValueBreakRuleException("this value can not be lower then 0");

        //vtCharacterValue.setValue(vtCharacterValue.getValue() + upDownArg);
        vtCharacterValue.modifyValueBy(upDownArg);
        state.setState(VTCharacterValueRulesChainState.EDIT);
    }

    @Override
    public int getOrder() {
        return 100;
    }
}
