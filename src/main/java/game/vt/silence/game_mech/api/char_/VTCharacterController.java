package game.vt.silence.game_mech.api.char_;

import game.vt.silence.exceptions.handlers.StatusRS;
import game.vt.silence.exceptions.handlers.StatusType;
import game.vt.silence.game_mech.api.char_.DTO.CharCreateRQ;
import game.vt.silence.game_mech.api.char_.DTO.CharRQ;
import game.vt.silence.game_mech.model.VTCharacter;
import game.vt.silence.game_mech.service.VTCharacterService;
import game.vt.silence.security.model.VTUser;
import game.vt.silence.security.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VTCharacterController {

    @Autowired
    VTCharacterService characterService;
    @Autowired
    SecurityService securityService;

    @PostMapping("/char/create")
    public StatusRS charCreate(@RequestBody CharCreateRQ request) {
        characterService.createVTCharacter(request.getCharname());

        VTUser vtUser = securityService.findLoggedInVT_User();
        VTCharacter vtCharacter = characterService.getVTCharacterByName(request.getCharname());

        characterService.addVTCharacter(vtUser, vtCharacter);

        return new StatusRS(StatusType.SUCCESS, "Character create success");
    }

    @PostMapping("/char")
    public VTCharacter charFind(@RequestBody CharRQ request) {
        return characterService.getVTCharacterByName(request.getCharname());
    }

    @PostMapping("/char/list")
    public String charList(@RequestBody CharCreateRQ request) {

        return "text";
    }


}
