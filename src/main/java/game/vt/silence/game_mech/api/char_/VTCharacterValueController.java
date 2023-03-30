package game.vt.silence.game_mech.api.char_;

import game.vt.silence.exceptions.handlers.StatusRS;
import game.vt.silence.exceptions.handlers.StatusType;
import game.vt.silence.game_mech.api.char_.DTO.CharEditRQ;
import game.vt.silence.game_mech.api.char_.DTO.CharValues;
import game.vt.silence.game_mech.model.VTCharacterValue;
import game.vt.silence.game_mech.service.administratio.VTCharacterValueEditorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VTCharacterValueController {

    @Autowired
    VTCharacterValueEditorService vtCharacterValueEditorService;

    @PostMapping("/char/edit")
    public StatusRS charEdit(@RequestBody CharEditRQ request) {
        vtCharacterValueEditorService.editValue(request.getCharname(), request.getSkillname(), request.getValue());
        return new StatusRS(StatusType.SUCCESS, "Value edit success");
    }

    @PostMapping("/char/values")
    public List<VTCharacterValue> charValues(@RequestBody CharValues request) {
        return vtCharacterValueEditorService.getValues(request.getCharname());
    }

}
