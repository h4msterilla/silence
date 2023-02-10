package game.vt.silence.game_mech.api.char_;

import com.fasterxml.jackson.databind.ObjectMapper;
import game.vt.silence.exceptions.VTCharacterNotFoundException;
import game.vt.silence.exceptions.NameOccupiedException;
import game.vt.silence.exceptions.VTCharacterValueWrongNameException;
import game.vt.silence.game_mech.api.char_.json.*;
import game.vt.silence.game_mech.model.VTCharacter;
import game.vt.silence.game_mech.service.VTCharacterService;
import game.vt.silence.game_mech.service.VTCharacterValueService;
import game.vt.silence.security.model.VTUser;
import game.vt.silence.security.service.SecurityService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CharController {

    ObjectMapper jackson = new ObjectMapper();
    @Autowired
    VTCharacterService characterService;
    @Autowired
    SecurityService securityService;
    @Autowired
    VTCharacterValueService vtCharacterValueService;

    @SneakyThrows
    @PostMapping("/char/create")
    public String charCreate(@RequestBody String json) {
        Char_Create_RQ request = jackson.readValue(json, Char_Create_RQ.class);

        try {
            characterService.createVTCharacter(request.getCharname());
        } catch (VTCharacterValueWrongNameException e) {
            return jackson.writeValueAsString(new Char_Create_RS("wrong charname", e.getWrongName()));
        } catch (NameOccupiedException e) {
            return jackson.writeValueAsString(new Char_Create_RS("charnamealreadyused", "sometext"));
        }

        VTUser vtUser = securityService.findLoggedInVT_User();
        VTCharacter vtCharacter = characterService.getVTCharacterByName(request.getCharname());

        characterService.addVTCharacter(vtUser, vtCharacter);

        return jackson.writeValueAsString(new Char_Create_RS("createsuccess",
                "user: " + vtUser.getUsername()
                        + " has create character: " + vtCharacter.getCharname()));
    }

    @SneakyThrows
    @PostMapping("/char")
    public String charFind(@RequestBody String json) {
        Char_RQ request = jackson.readValue(json, Char_RQ.class);
        VTCharacter vtCharacter = null;

        try {
            vtCharacter = characterService.getVTCharacterByName(request.getCharname());
        } catch (VTCharacterNotFoundException e) {
            return jackson.writeValueAsString(new Char_RS("wrong charname", "character not found"));
        }

        return jackson.writeValueAsString(vtCharacter);
    }

    @SneakyThrows
    @PostMapping("/char/edit")
    public String charEdit(@RequestBody String json) {
        Char_Edit_RQ request = jackson.readValue(json, Char_Edit_RQ.class);

        try {
            vtCharacterValueService.edit(request.getCharname(),request.getSkillname(),request.getValue());
            //characterService.changeCharValueByName(request.getCharname(), request.getSkillname(), request.getValue());
        } catch (VTCharacterValueWrongNameException e) {
            return jackson.writeValueAsString(new Char_Edit_RS("wrongedit", e.getWrongName()));
        } catch (VTCharacterNotFoundException e) {
            return jackson.writeValueAsString(new Char_Edit_RS("wrong charname", "characher not found"));
        }

        return jackson.writeValueAsString(new Char_Edit_RS("edit success", "some text"));
    }

    @SneakyThrows
    @PostMapping("/char/list")
    public String charList(@RequestBody String json){
        Char_Create_RQ request = jackson.readValue(json, Char_Create_RQ.class);




        return "text";
    }


}
