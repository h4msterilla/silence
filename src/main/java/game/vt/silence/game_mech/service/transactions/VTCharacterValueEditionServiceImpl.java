package game.vt.silence.game_mech.service.transactions;

import game.vt.silence.game_mech.model.VTValue;
import game.vt.silence.game_mech.service.operarius.VTCharacterService;
import game.vt.silence.game_mech.service.operarius.VTCharacterValueService;
import game.vt.silence.game_mech.vtcharacterrules.VTCharacterValueRulesChain;
import game.vt.silence.game_mech.model.VTCharacter;
import game.vt.silence.game_mech.model.VTCharacterValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VTCharacterValueEditionServiceImpl implements VTCharacterValueEditionService {

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
        VTValue vtValue = VTValue.valueOf(valueName.toUpperCase());
        //System.out.println(vtValue + " -=to edit=-");
        VTCharacterValue vtCharacterValue = vtCharacterValues
                .stream()
                //.filter(x -> x.getName().equalsIgnoreCase(valueName))
                .filter(x -> x.equalsVTValue(vtValue))
                .findAny()
                .get();

        vtCharacterValueRulesChain.doChain(vtCharacterValues, vtCharacterValue, upDown);
        vtCharacterValueService.save(vtCharacterValues);
    }

    @Override
    public List<VTCharacterValue> getValues(String charName) {
        VTCharacter vtCharacter = vtCharacterService.getVTCharacterByName(charName);
        return vtCharacterValueService.getVTCharacterValuesByVTCharacter(vtCharacter);
    }
}