package game.vt.silence.game_mech.service.operarius;

import game.vt.silence.exceptions.VTCharacterNameOccupiedException;
import game.vt.silence.exceptions.VTCharacterNotFoundException;
import game.vt.silence.game_mech.model.VTCharacter;
import game.vt.silence.game_mech.model.VTCharacterValue;
import game.vt.silence.game_mech.repo.VTCharacterRepo;
import game.vt.silence.security.model.VTUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VTCharacterServiceImpl implements VTCharacterService {

    @Autowired
    VTCharacterRepo repo;

    @Override
    public VTCharacter getVTCharacterByName(String value_name) {
        if (!existsVTCharacterByName(value_name)) throw new VTCharacterNotFoundException();
        return repo.findByCharname(value_name);
    }

    @Override
    public boolean existsVTCharacterByName(String value_name) {
        return repo.existsByCharname(value_name);
    }

    @Override
    public void saveVTCharacter(VTCharacter character) {
        repo.save(character);
    }

    @Override
    public void createVTCharacter(String value_name) {
        if (repo.existsByCharname(value_name)) throw new VTCharacterNameOccupiedException();
        VTCharacter character = new VTCharacter();
        character.setCharname(value_name);
        saveVTCharacter(character);
    }

    @Override
    public void addVTUser(VTCharacter character, VTUser user) {
        character.setVtUser(user);
        repo.save(character);
    }

    @Override
    public void addVTCharacterValue(VTCharacter vtCharacter, VTCharacterValue vtCharacterValue) {
        vtCharacter.addVTCharacterValue(vtCharacterValue);
        repo.save(vtCharacter);
    }

    @Override
    public void addVTCharacterValue(VTCharacter vtCharacter, List<VTCharacterValue> vtCharacterValues) {
        vtCharacterValues.forEach(x -> this.addVTCharacterValue(vtCharacter, x));
    }

}
