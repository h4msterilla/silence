package game.vt.silence.game_mech.service;

import game.vt.silence.exceptions.VTCharacterNameOccupiedException;
import game.vt.silence.exceptions.VTCharacterNotFoundException;
import game.vt.silence.game_mech.model.VTCharacter;
import game.vt.silence.game_mech.repo.VTCharacterRepo;
import game.vt.silence.security.model.VTUser;
import game.vt.silence.security.service.VTUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VTCharacterServiceImpl implements VTCharacterService {

    private static final Logger logger = LoggerFactory.getLogger(VTCharacterServiceImpl.class);

    @Autowired
    VTCharacterRepo repo;
    @Autowired
    VTUserService userService;

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

    /*@Override
    public void changeCharValueByName(String character, String valueName, String up_down) throws WrongCharacterValueNameException, CharacterNotFoundException {
        //VTCharacter vt_Character = getVTCharacterByName(character);
        //vt_Character.changeValueByName(valueName, up_down);//-------------------------------------------------!
        //saveVTCharacter(vt_Character);
    }*/

    @Override
    public void createVTCharacter(String value_name) {
        if (repo.existsByCharname(value_name)) throw new VTCharacterNameOccupiedException();

        VTCharacter character = new VTCharacter();
        character.setCharname(value_name);
        saveVTCharacter(character);
        logger.info("create new character - {}", character.getCharname());
    }

    @Override
    public void addVTCharacter(VTUser user, VTCharacter character) {
        userService.addVTCharacter(user, character);
        character.setVtUser(user);
        repo.save(character);
        logger.info("add character {} to user {}", character.getCharname(), user.getUsername());
    }

}
