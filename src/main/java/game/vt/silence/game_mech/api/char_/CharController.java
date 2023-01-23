package game.vt.silence.game_mech.api.char_;

import com.fasterxml.jackson.databind.ObjectMapper;
import game.vt.silence.game_mech.api.char_.json.*;
import game.vt.silence.game_mech.model.CharacterNotFoundException;
import game.vt.silence.game_mech.model.NameOccupiedException;
import game.vt.silence.game_mech.model.VT_Character;
import game.vt.silence.game_mech.model.WrongCharacterValueNameException;
import game.vt.silence.game_mech.service.VT_CharacterService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CharController {

    ObjectMapper jackson = new ObjectMapper();
    @Autowired
    VT_CharacterService characterService;

    @SneakyThrows
    @PostMapping("/char/create")
    public String charCreate(@RequestBody String json) {
        Char_Create_RQ request = jackson.readValue(json, Char_Create_RQ.class);

        try {
            characterService.createCharacter(request.getCharname());
        } catch (WrongCharacterValueNameException e) {
            return jackson.writeValueAsString(new Char_Create_RS("wrong charname",e.getWrongName()));
        } catch (NameOccupiedException e) {
            return jackson.writeValueAsString(new Char_Create_RS("charnamealreadyused","sometext"));
        }

        return jackson.writeValueAsString(new Char_Create_RS("createsuccess","sometext"));
    }

    @SneakyThrows
    @PostMapping("/char")
    public String charFind(@RequestBody String json) {
        Char_RQ request = jackson.readValue(json, Char_RQ.class);
        VT_Character character = null;

        try {
            character = characterService.getVT_CharacterByName(request.getCharname());
        } catch (CharacterNotFoundException e) {
            return jackson.writeValueAsString(new Char_RS("wrong charname","character not found"));
        }

        return jackson.writeValueAsString(character);
    }

    @SneakyThrows
    @PostMapping("/char/edit")
    public String charEdit(@RequestBody String json){
        Char_Edit_RQ request = jackson.readValue(json, Char_Edit_RQ.class);

        try {
            characterService.changeCharValueByName(request.getCharname(),request.getSkillname(),request.getValue());
        } catch (WrongCharacterValueNameException e) {
            return jackson.writeValueAsString(new Char_Edit_RS("wrongedit",e.getWrongName()));
        } catch (CharacterNotFoundException e) {
            return jackson.writeValueAsString(new Char_Edit_RS("wrong charname","characher not found"));
        }

        return jackson.writeValueAsString(new Char_Edit_RS("edit success","some text"));
    }


}
