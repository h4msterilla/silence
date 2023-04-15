package game.vt.silence.game_mech.vtcharacterrules;

import game.vt.silence.game_mech.model.VTValue;
import game.vt.silence.game_mech.model.VTValueTag;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter//tmp
@Setter//tmp
public class VTCharacterValueRulesChainStateHandler {

    private boolean isEdit = false;

    private List<VTCharacterValueRulesChainState> states = new ArrayList<>();

    public VTCharacterValueRulesChainStateHandler add(VTCharacterValueRulesChainState state){
        isEdit = true;
        if(!states.contains(state))
            states.add(state);
        return this;
    }

    public boolean contains(VTCharacterValueRulesChainState state){
        return states.contains(state);
    }

    public boolean isEdit(){
        return isEdit;
    }

    public VTValueTag adaptToVTValueTagBlock(){
        if(states.contains(VTCharacterValueRulesChainState.EDIT_SKILL_ENDURANCE))return VTValueTag.BLOCK_ENDURANCE;
        if(states.contains(VTCharacterValueRulesChainState.EDIT_SKILL_SWIFTNESS))return VTValueTag.BLOCK_SWIFTNESS;
        if(states.contains(VTCharacterValueRulesChainState.EDIT_SKILL_MIND))return VTValueTag.BLOCK_MIND;
        if(states.contains(VTCharacterValueRulesChainState.EDIT_SKILL_EGO))return VTValueTag.BLOCK_EGO;

        throw new IllegalArgumentException();
    }

    public VTValue adaptToVTValueBlock(){
        if(states.contains(VTCharacterValueRulesChainState.EDIT_SKILL_ENDURANCE))return VTValue.BLOCK_ENDURANCE;
        if(states.contains(VTCharacterValueRulesChainState.EDIT_SKILL_SWIFTNESS))return VTValue.BLOCK_SWIFTNESS;
        if(states.contains(VTCharacterValueRulesChainState.EDIT_SKILL_MIND))return VTValue.BLOCK_MIND;
        if(states.contains(VTCharacterValueRulesChainState.EDIT_SKILL_EGO))return VTValue.BLOCK_EGO;
        throw new IllegalArgumentException();
    }

    VTCharacterValueRulesChainState state = VTCharacterValueRulesChainState.NOT_EDIT;//tmp
}
