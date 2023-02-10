package game.vt.silence.game_mech.api.char_;

import com.fasterxml.jackson.databind.ObjectMapper;
import game.vt.silence.exceptions.VTCharacterNotFoundException;
import game.vt.silence.exceptions.VTCharacterValueNotFoundException;
import game.vt.silence.game_mech.api.char_.json.Char_Edit_RQ;
import game.vt.silence.game_mech.api.char_.json.Char_Edit_RS;
import game.vt.silence.game_mech.service.VTCharacterService;
import game.vt.silence.game_mech.service.VTCharacterValueService;
import game.vt.silence.security.service.SecurityService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VTCharacterValueController {

    @Autowired
    ObjectMapper jackson;
    @Autowired
    VTCharacterValueService vtCharacterValueService;

    @SneakyThrows
    @PostMapping("/char/edit")
    public String charEdit(@RequestBody String json) {
        Char_Edit_RQ request = jackson.readValue(json, Char_Edit_RQ.class);

        try {
            vtCharacterValueService.edit(request.getCharname(),request.getSkillname(),request.getValue());
            //characterService.changeCharValueByName(request.getCharname(), request.getSkillname(), request.getValue());
        } catch (VTCharacterValueNotFoundException e) {
            return jackson.writeValueAsString(new Char_Edit_RS("wrongedit", e.getWrongName()));
        } catch (VTCharacterNotFoundException e) {
            return jackson.writeValueAsString(new Char_Edit_RS("wrong charname", "characher not found"));
        }

        return jackson.writeValueAsString(new Char_Edit_RS("edit success", "some text"));
    }


}
