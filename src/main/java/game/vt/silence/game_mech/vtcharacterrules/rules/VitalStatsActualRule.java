package game.vt.silence.game_mech.vtcharacterrules.rules;

import game.vt.silence.exceptions.VTCharacterValueBreakRuleException;
import game.vt.silence.game_mech.model.values.VTCharacterValue;
import game.vt.silence.game_mech.model.values.VTValue;
import game.vt.silence.game_mech.vtcharacterrules.VTCharacterValueRulesChainState;
import game.vt.silence.game_mech.vtcharacterrules.VTCharacterValueRulesChainStateHandler;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class VitalStatsActualRule implements VTCharacterValueRule {
    @Override
    public void doRule(Map<VTValue, VTCharacterValue> vtValueMap, VTCharacterValue vtCharacterValue, int upDownArg, VTCharacterValueRulesChainStateHandler state) {

        //if(!(vtCharacterValue.getName().equals("vital_stats_health_actual") ||
        //        vtCharacterValue.getName().equals("vital_stats_sanity_actual"))) return;
        if (!(vtCharacterValue.equalsVTValue(VTValue.VITAL_STATS_HEALTH_ACTUAL)
                || vtCharacterValue.equalsVTValue(VTValue.VITAL_STATS_SANITY_ACTUAL))) return;

        if (vtCharacterValue.getValue() + upDownArg < 0)
            throw new VTCharacterValueBreakRuleException("this value can not be lower then 0");

        //if(vtCharacterValue.getName().equals("vital_stats_health_actual") &&
        //        vtValueMap.get("vital_stats_health_max").getValue()
        //                + vtValueMap.get("stats_health_add").getValue()
        //                < vtCharacterValue.getValue() + upDownArg)
        //    throw new VTCharacterValueBreakRuleException("actual health can not be higher then sum of max and add health");
        if (vtCharacterValue.equalsVTValue(VTValue.VITAL_STATS_HEALTH_ACTUAL)
                && (vtValueMap.get(VTValue.VITAL_STATS_HEALTH_MAX).getValue()
                + vtValueMap.get(VTValue.VITAL_STATS_HEALTH_ADD).getValue()
                < vtCharacterValue.getValue() + upDownArg))
            throw new VTCharacterValueBreakRuleException("actual health can not be higher then sum of max and add health");

        //if (vtCharacterValue.getName().equals("vital_stats_sanity_actual") &&
        //        vtValueMap.get("vital_stats_sanity_max").getValue()
        //                + vtValueMap.get("stats_sanity_add").getValue()
        //                < vtCharacterValue.getValue() + upDownArg)
        //    throw new VTCharacterValueBreakRuleException("actual health can not be higher then sum of max and add sanity");
        if (vtCharacterValue.equalsVTValue(VTValue.VITAL_STATS_SANITY_ACTUAL)
                && (vtValueMap.get(VTValue.VITAL_STATS_SANITY_MAX).getValue()
                + vtValueMap.get(VTValue.VITAL_STATS_SANITY_ADD).getValue()
                < vtCharacterValue.getValue() + upDownArg))
            throw new VTCharacterValueBreakRuleException("actual sanity can not be higher then sum of max and add sanity");

        //vtCharacterValue.setValue(vtCharacterValue.getValue() + upDownArg);
        vtCharacterValue.modifyValueBy(upDownArg);
        state.add(VTCharacterValueRulesChainState.EDIT);
        //state.setState(VTCharacterValueRulesChainState.EDIT);
    }

    @Override
    public int getOrder() {
        return 22;
    }
}
