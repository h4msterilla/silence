package game.vt.silence.game_mech.api.char_;

import game.vt.silence.game_mech.api.char_.DTO.CharEditRQ;
import game.vt.silence.game_mech.api.char_.DTO.CharEditRS;
import game.vt.silence.game_mech.service.VTCharacterValueService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VTCharacterValueController {

    @Autowired
    VTCharacterValueService vtCharacterValueService;

    @PostMapping("/char/edit")
    public CharEditRS charEdit(@RequestBody CharEditRQ request) {
        vtCharacterValueService.edit(request.getCharname(), request.getSkillname(), request.getValue());
        return new CharEditRS("edit success", "some text");
    }


}
