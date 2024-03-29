package game.vt.silence.game_mech.service.administratio;

import game.vt.silence.game_mech.model.VTCharacter;
import game.vt.silence.game_mech.model.values.VTCharacterValue;
import game.vt.silence.game_mech.service.VTCharacterService;
import game.vt.silence.game_mech.service.VTCharacterValueService;
import game.vt.silence.security.model.VTUser;
import game.vt.silence.security.service.VTUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Deprecated
public class VTCharacterInitServiceImpl implements VTCharacterInitService {

    private static final Logger logger = LoggerFactory.getLogger(VTCharacterInitServiceImpl.class);

    @Autowired
    VTCharacterService vtCharacterService;
    @Autowired
    VTCharacterValueService vtCharacterValueService;
    @Autowired
    VTUserService vtUserService;

    @Override
    public void init(String vtCharacterName, String vtUserName) {
        vtCharacterService.createVTCharacter(vtCharacterName);

        VTCharacter vtCharacter = vtCharacterService.getVTCharacterByName(vtCharacterName);
        List<VTCharacterValue> vtCharacterValues = vtCharacterValueService.getDefaultVTCharacterValuesList();
        VTUser vtUser = vtUserService.findByUsername(vtUserName);
        vtCharacterValueService.setVTCharacter(vtCharacterValues, vtCharacter);//691 ms
        vtCharacterService.addVTCharacterValue(vtCharacter,vtCharacterValues);//1804 ms
        vtCharacterService.addVTUser(vtCharacter, vtUser);
        vtUserService.addVTCharacter(vtUser,vtCharacter);

        logger.info("create new character - {} - by user - {}", vtCharacter.getCharname(), vtUser.getUsername());
    }

}
