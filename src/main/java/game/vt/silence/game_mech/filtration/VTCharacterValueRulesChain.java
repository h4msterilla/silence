package game.vt.silence.game_mech.filtration;

import game.vt.silence.exceptions.VTCharacterValueNotFoundException;
import game.vt.silence.game_mech.model.VTCharacterValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component
public class VTCharacterValueRulesChain {

    @Autowired
    List<VTCharacterValueRule> rules;

    public void doChain(List<VTCharacterValue> vtCharacterValues, VTCharacterValue vtCharacterValue, String upDown){
        VTCharacterValueRulesChainState state = new VTCharacterValueRulesChainState();
        rules
                .stream()
                .sorted(Comparator.comparingInt(VTCharacterValueRule::getOrder))
                .forEach(x -> x.doRule(vtCharacterValues, vtCharacterValue, upDown, state));
        if(state.getState() == VTCharacterValueRulesChainStates.NOT_EDIT) throw new VTCharacterValueNotFoundException();
    }

}
