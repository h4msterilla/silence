package game.vt.silence.game_mech.filtration.filters;

import game.vt.silence.game_mech.filtration.VTCharacterValueRule;
import game.vt.silence.game_mech.filtration.VTCharacterValueRulesChainState;
import game.vt.silence.game_mech.model.VTCharacterValue;

import java.util.Map;

public class VitalStatsAddRule implements VTCharacterValueRule {
    @Override
    public void doRule(Map<String, VTCharacterValue> vtValueMap, VTCharacterValue vtCharacterValue, String upDown, VTCharacterValueRulesChainState state) {

        if(!(vtCharacterValue.getName().equals("vital_stats_health_add") ||
        vtCharacterValue.getName().equals("vital_stats_sanity_add"))) return;



    }

    @Override
    public int getOrder() {
        return 21;
    }
}
