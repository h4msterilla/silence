package game.vt.silence.game_mech.filtration;

import game.vt.silence.exceptions.VTCharacterValueNotFoundException;
import game.vt.silence.game_mech.model.VTCharacterValue;
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

        Map<String, VTCharacterValue> vtValueMap = vtCharacterValues.stream()
                .collect(Collectors.toMap(x -> x.getName(), Function.identity()));

        VTCharacterValueRulesChainState state = new VTCharacterValueRulesChainState();
        rules
                .stream()
                .sorted(Comparator.comparingInt(VTCharacterValueRule::getOrder))
                .forEach(x -> x.doRule(vtValueMap, vtCharacterValue, upDown, state));

        if (state.getState() == VTCharacterValueRulesChainStates.NOT_EDIT)
            throw new VTCharacterValueNotFoundException();
    }

}