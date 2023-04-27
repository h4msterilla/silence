package game.vt.silence.game_mech.service.transactions.impl;

import game.vt.silence.game_mech.model.VTCharacter;
import game.vt.silence.game_mech.model.values.VTCharacterValue;
import game.vt.silence.game_mech.model.values.VTValue;
import game.vt.silence.game_mech.service.VTCharacterService;
import game.vt.silence.game_mech.service.VTCharacterValueService;
import game.vt.silence.game_mech.service.transactions.VTCharacterValueEditionService;
import game.vt.silence.game_mech.vtcharacterrules.VTCharacterValueRulesChain;
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
    public void editValue4Vaadin(VTCharacter vtCharacter,List<VTCharacterValue> vtCharacterValueList,VTCharacterValue vtCharacterValue, String upDown) {
        //System.out.println("here 1 = " + vtCharacterValue.toString());
        vtCharacterValueRulesChain.doChain(vtCharacterValueList, vtCharacterValue, upDown);
        //System.out.println("here 2 = " + vtCharacterValueList);
        vtCharacterValueService.save(vtCharacterValueList);
    }

    @Override
    public List<VTCharacterValue> getValues(String charName) {
        VTCharacter vtCharacter = vtCharacterService.getVTCharacterByName(charName);
        return vtCharacterValueService.getVTCharacterValuesByVTCharacter(vtCharacter);
    }
}
