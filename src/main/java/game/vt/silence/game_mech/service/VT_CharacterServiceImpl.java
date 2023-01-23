package game.vt.silence.game_mech.service;

import game.vt.silence.game_mech.model.CharacterNotFoundException;
import game.vt.silence.game_mech.model.NameOccupiedException;
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
    public VT_Character getVT_CharacterByName(String value_name) throws CharacterNotFoundException {
        if(!existsVT_CharacterByName(value_name)) throw new CharacterNotFoundException();
        return repo.findByValue_name(value_name);
    }

    @Override
    public boolean existsVT_CharacterByName(String value_name) {
        return repo.existsByValue_name(value_name);
    }

    @Override
    public void saveCharacter(VT_Character character) {
        repo.save(character);
    }

    @Override
    public void changeCharValueByName(String character, String valueName, String up_down) throws WrongCharacterValueNameException, CharacterNotFoundException {
        VT_Character vt_Character = getVT_CharacterByName(character);
        vt_Character.changeValueByName(valueName, up_down);
        saveCharacter(vt_Character);
    }

    @Override
    public void createCharacter(String value_name) throws WrongCharacterValueNameException, NameOccupiedException {
        if (repo.existsByValue_name(value_name)) throw new NameOccupiedException();

        VT_Character character = new VT_Character();
        character.setValue_name(value_name);
        saveCharacter(character);
        logger.info("create new character - {}",character.getValue_name());
    }

}
