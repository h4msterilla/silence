package game.vt.silence.game_mech.vtcharacterrules;

import game.vt.silence.exceptions.VTCharacterValueBreakRuleException;
import game.vt.silence.exceptions.VTCharacterValueNotFoundException;
import game.vt.silence.game_mech.model.values.VTCharacterValue;
import game.vt.silence.game_mech.model.values.VTValue;
import game.vt.silence.game_mech.vtcharacterrules.rules.VTCharacterValueRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class VTCharacterValueRulesChain {

    @Autowired
    List<VTCharacterValueRule> rules;

    public void doChain(List<VTCharacterValue> vtCharacterValues, VTCharacterValue vtCharacterValue, String upDown) {

        if (!(upDown.equalsIgnoreCase("up") || upDown.equalsIgnoreCase("down")))
            throw new VTCharacterValueBreakRuleException("wrong up/down argument");

        int upDownArg;

        if(upDown.equalsIgnoreCase("up"))
            upDownArg = 1;
        else
            upDownArg = -1;

        Map<VTValue, VTCharacterValue> vtValueMap = vtCharacterValues.stream()
                .collect(Collectors.toMap(x -> x.getValueName(), Function.identity()));

        VTCharacterValueRulesChainStateHandler state = new VTCharacterValueRulesChainStateHandler();
        rules
                .stream()
                .sorted(Comparator.comparingInt(VTCharacterValueRule::getOrder))
                .forEach(x -> {x.doRule(vtValueMap, vtCharacterValue, upDownArg, state);
                        //System.out.println(x.getClass().getSimpleName());
                });

        //if (state.getState() == VTCharacterValueRulesChainState.NOT_EDIT)
        if(!state.isEdit())
            throw new VTCharacterValueNotFoundException();
    }

}
