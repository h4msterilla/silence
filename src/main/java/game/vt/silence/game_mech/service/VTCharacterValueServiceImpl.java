package game.vt.silence.game_mech.service;

import game.vt.silence.beans.DefaultVTCharacterValuesLoader;
import game.vt.silence.game_mech.model.VTCharacter;
import game.vt.silence.game_mech.model.VTCharacterValue;
import game.vt.silence.game_mech.model.factories.VTCharacterValueListFactory;
import game.vt.silence.game_mech.repo.VTCharacterValueRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VTCharacterValueServiceImpl implements VTCharacterValueService {

    @Autowired
    VTCharacterValueRepo vtCharacterValueRepo;

    @Override
    public void save(VTCharacterValue vtCharacterValue) {
        vtCharacterValueRepo.save(vtCharacterValue);
    }

    @Override
    public void save(List<VTCharacterValue> vtCharacterValues) {
        vtCharacterValues.forEach(this::save);
    }

    @Override
    public void setVTCharacter(VTCharacterValue vtCharacterValue, VTCharacter vtCharacter) {
        vtCharacterValue.setVtCharacter(vtCharacter);
        save(vtCharacterValue);
    }

    @Override
    public void setVTCharacter(List<VTCharacterValue> vtCharacterValues, VTCharacter vtCharacter) {
        vtCharacterValues.forEach(x -> x.setVtCharacter(vtCharacter));
        save(vtCharacterValues);
    }

    //@Autowired
    //DefaultVTCharacterValuesLoader loader;

    @Autowired
    VTCharacterValueListFactory vtCharacterValueListFactory;

    @Override
    public List<VTCharacterValue> getDefaultVTCharacterValuesList() {
        return vtCharacterValueListFactory.getNewVTCharacterValueList();
    }

    @Override
    public List<VTCharacterValue> getVTCharacterValuesByVTCharacter(VTCharacter vtCharacter) {
        return vtCharacterValueRepo.findByVtCharacter(vtCharacter);
    }

}
