package game.vt.silence.game_mech.api.char_;

import game.vt.silence.exceptions.handlers.StatusRS;
import game.vt.silence.exceptions.handlers.StatusType;
import game.vt.silence.game_mech.api.char_.DTO.CharCreateRQ;
import game.vt.silence.game_mech.api.char_.DTO.CharListRQ;
import game.vt.silence.game_mech.api.char_.DTO.CharRQ;
import game.vt.silence.game_mech.model.VTCharacter;
import game.vt.silence.game_mech.service.VTCharacterService;
import game.vt.silence.game_mech.service.transactions.VTCharacterCreationService;
import game.vt.silence.security.service.SecurityService;
import game.vt.silence.security.service.VTUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VTCharacterController {

    @Autowired
    VTCharacterService characterService;
    //@Autowired
    //VTCharacterInitService vtCharacterInitService;
    @Autowired
    VTCharacterCreationService vtCharacterCreationService;
    @Autowired
    VTUserService vtUserService;
    @Autowired
    SecurityService securityService;

    @PostMapping("/api/char/create")
    public StatusRS charCreate(@RequestBody CharCreateRQ request) {
        //System.out.println(securityService.findLoggedInVT_User().getUsername());
        //vtCharacterInitService.init(request.getCharname(), securityService.findLoggedInVT_User().getUsername());
        vtCharacterCreationService.create(request.getCharname(), securityService.findLoggedInUsername());
        return new StatusRS(StatusType.SUCCESS, "Character create success");
    }

    @PostMapping("/api/char")
    public VTCharacter charFind(@RequestBody CharRQ request) {
        return characterService.getVTCharacterByName(request.getCharname());
    }

    @PostMapping("/api/char/list")
    public List<VTCharacter> charList(@RequestBody CharListRQ request) {
        return vtUserService.getVTCharactersByUsername(request.getUsername());
    }
}
