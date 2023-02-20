package game.vt.silence.game_mech.filtration.filters;

import game.vt.silence.exceptions.VTCharacterValueBreakRuleException;
import game.vt.silence.game_mech.filtration.VTCharacterValueRule;
import game.vt.silence.game_mech.filtration.VTCharacterValueRulesChainState;
import game.vt.silence.game_mech.filtration.VTCharacterValueRulesChainStates;
import game.vt.silence.game_mech.model.VTCharacterValue;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ExpMaxRule implements VTCharacterValueRule {
    @Override
    public void doRule(Map<String, VTCharacterValue> vtValueMap, VTCharacterValue vtCharacterValue, int upDownArg, VTCharacterValueRulesChainState state) {

        if(!vtCharacterValue.getName().equalsIgnoreCase("value_experience_max")) return;

        if(vtCharacterValue.getValue() + upDownArg < vtValueMap.get("value_experience_actual").getValue())
            throw new VTCharacterValueBreakRuleException("downgrade some skill before setting this value lower then exp actual");

        vtCharacterValue.setValue(vtCharacterValue.getValue() + upDownArg);
        state.setState(VTCharacterValueRulesChainStates.EDIT_UNLINKED);
    }

    @Override
    public int getOrder() {
        return 3;
    }
}
