package game.vt.silence.game_mech.service.alayer;

import game.vt.silence.game_mech.model.VTCharacter;
import game.vt.silence.game_mech.model.VTCharacterValue;
import game.vt.silence.game_mech.service.datasavelayer.VTCharacterService;
import game.vt.silence.game_mech.service.datasavelayer.VTCharacterValueService;
import game.vt.silence.security.model.VTUser;
import game.vt.silence.security.service.SecurityService;
import game.vt.silence.security.service.VTUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VTCharacterInitServiceImpl implements VTCharacterInitService {

    private static final Logger logger = LoggerFactory.getLogger(VTCharacterInitServiceImpl.class);

    @Autowired
    VTCharacterService vtCharacterService;
    @Autowired
    VTCharacterValueService vtCharacterValueService;
    @Autowired
    VTUserService vtUserService;
    @Autowired
    SecurityService securityService;

    @Override
    public void init(String characterName) {
        vtCharacterService.createVTCharacter(characterName);

        VTCharacter vtCharacter = vtCharacterService.getVTCharacterByName(characterName);
        List<VTCharacterValue> vtCharacterValues = vtCharacterValueService.getDefaultVTCharacterValuesList();
        VTUser vtUser = securityService.findLoggedInVT_User();

        vtCharacterValueService.setVTCharacter(vtCharacterValues, vtCharacter);
        vtCharacterService.addVTUser(vtCharacter, vtUser);
        vtCharacterService.addVTCharacterValue(vtCharacter, vtCharacterValues);
        vtUserService.addVTCharacter(vtUser, vtCharacter);

        logger.info("create new character - {} - by user - {}", characterName, vtUser.getUsername());
    }
}
