package game.vt.silence.game_mech.service;

import game.vt.silence.game_mech.filtration.VTCharacterValueRulesChain;
import game.vt.silence.game_mech.model.VTCharacter;
import game.vt.silence.game_mech.model.VTCharacterValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VTCharacterValueEditorServiceImpl implements VTCharacterValueEditorService {

    @Autowired
    VTCharacterService vtCharacterService;
    @Autowired
    VTCharacterValueService vtCharacterValueService;
    @Autowired
    VTCharacterValueRulesChain vtCharacterValueRulesChain;

    @Override
    public void editValue(String charName, String valueName, String upDown) {

        VTCharacter vtCharacter = vtCharacterService.getVTCharacterByName(charName);
        List<VTCharacterValue> vtCharacterValues = vtCharacterValueService.getVTCharacterValuesByVTCharacter(vtCharacter);
        VTCharacterValue vtCharacterValue = vtCharacterValues
                .stream()
                .filter(x -> x.getName().equalsIgnoreCase(valueName))
                .findAny()
                .get();

        vtCharacterValueRulesChain.doChain(vtCharacterValues, vtCharacterValue, upDown);

        vtCharacterValueService.save(vtCharacterValues);
    }
}
