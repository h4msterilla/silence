package game.vt.silence.game_mech.api.char_;

import game.vt.silence.exceptions.VTCharacterNotFoundException;
import game.vt.silence.exceptions.VTCharacterValueNotFoundException;
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

    @SneakyThrows
    @PostMapping("/char/edit")
    public CharEditRS charEdit(@RequestBody CharEditRQ request ) {

        try {
            vtCharacterValueService.edit(request.getCharname(),request.getSkillname(),request.getValue());
        } catch (VTCharacterValueNotFoundException e) {
            return new CharEditRS("wrongedit", e.getMessage());
        } catch (VTCharacterNotFoundException e) {
            return new CharEditRS("wrong charname", e.getMessage());
        }

        return new CharEditRS("edit success", "some text");
    }


}
