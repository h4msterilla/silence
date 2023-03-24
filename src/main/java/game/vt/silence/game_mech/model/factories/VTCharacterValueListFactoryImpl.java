package game.vt.silence.game_mech.model.factories;

import game.vt.silence.game_mech.model.VTCharacterValue;
import game.vt.silence.game_mech.model.VTValue;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class VTCharacterValueListFactoryImpl implements VTCharacterValueListFactory {
    @Override
    public List<VTCharacterValue> getNewVTCharacterValueList() {
        List<VTCharacterValue> vtCharacterValueList = new ArrayList<>();
        Arrays.stream(VTValue.values()).forEach(x -> vtCharacterValueList.add(getNewVTCharacterValue(x)));
        return vtCharacterValueList;
    }

    private VTCharacterValue getNewVTCharacterValue(VTValue vtValue) {
        VTCharacterValue vtCharacterValue = new VTCharacterValue();
        vtCharacterValue.setValueName(vtValue);
        vtCharacterValue.setValue(0);
        return vtCharacterValue;
    }

}
