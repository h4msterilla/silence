package game.vt.silence.game_mech.service;

import game.vt.silence.game_mech.model.VT_Character;
import game.vt.silence.game_mech.model.WrongCharacterValueNameException;
import game.vt.silence.game_mech.repo.VT_CharacterRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VT_CharacterServiceImpl implements VT_CharacterService {

    private static final Logger logger = LoggerFactory.getLogger(VT_CharacterServiceImpl.class);

    @Autowired
    VT_CharacterRepo repo;

    @Override
    public VT_Character getVT_CharacterByName(String name) {
        return repo.findByValue_name(name);
    }

    @Override
    public void saveCharacter(VT_Character character) {
        repo.save(character);
    }

    @Override
    public void changeCharValueByName(VT_Character character, String valueName,String up_down) throws WrongCharacterValueNameException {
        character.changeValueByName(valueName,up_down);
    }

    @Override
    public void createCharacter(String value_name) {
        VT_Character character = new VT_Character();
        character.setValue_name(value_name);
        saveCharacter(character);
    }

}
