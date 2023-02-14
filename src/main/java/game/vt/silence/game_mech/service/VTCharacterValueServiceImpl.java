package game.vt.silence.game_mech.service;

import game.vt.silence.beans.DefaultVTCharacterValuesLoader;
import game.vt.silence.exceptions.VTCharacterValueNotFoundException;
import game.vt.silence.game_mech.model.VTCharacter;
import game.vt.silence.game_mech.model.VTCharacterValue;
import game.vt.silence.game_mech.repo.VTCharacterValueRepo;
import game.vt.silence.game_mech.validator.VTCharacterValueValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VTCharacterValueServiceImpl implements VTCharacterValueService {

    @Autowired
    VTCharacterService vtCharacterService;
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

    @Autowired
    VTCharacterValueValidator vtCharacterValueValidator;

    @Override
    public void edit(String vtCharname, String vtCharacterValue, String upDown) {
        VTCharacter vtCharacter = vtCharacterService.getVTCharacterByName(vtCharname);

        Optional<VTCharacterValue> value = vtCharacterValueRepo
                .findByVtCharacter(vtCharacter)
                .stream()
                .filter(v -> v.getName().equalsIgnoreCase(vtCharacterValue))
                .findFirst();

        if (value.isEmpty()) throw new VTCharacterValueNotFoundException();

        vtCharacterValueValidator.validate(vtCharacter, value.get(), upDown);

        if (upDown.equalsIgnoreCase("up"))
            value.get().setValue(
                    value.get().getValue() + 1);
        if (upDown.equalsIgnoreCase("down"))
            value.get().setValue(
                    value.get().getValue() - 1);

        vtCharacterValueRepo.save(value.get());
    }

    @Autowired
    DefaultVTCharacterValuesLoader loader;

    @Override
    public List<VTCharacterValue> getDefaultVTCharacterValuesList() {
        return loader.getAsList();
    }

}
