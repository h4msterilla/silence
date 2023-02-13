package game.vt.silence.game_mech.api.char_;

import game.vt.silence.exceptions.handlers.StatusRS;
import game.vt.silence.exceptions.handlers.StatusType;
import game.vt.silence.game_mech.api.char_.DTO.CharEditRQ;
import game.vt.silence.game_mech.service.VTCharacterValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VTCharacterValueController {

    @Autowired
    VTCharacterValueService vtCharacterValueService;

    @PostMapping("/char/edit")
    public StatusRS charEdit(@RequestBody CharEditRQ request) {
        vtCharacterValueService.edit(request.getCharname(), request.getSkillname(), request.getValue());
        return new StatusRS(StatusType.SUCCESS,"Value edit success");
    }


}
