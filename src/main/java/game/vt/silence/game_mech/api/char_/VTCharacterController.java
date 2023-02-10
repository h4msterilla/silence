package game.vt.silence.game_mech.api.char_;

import com.fasterxml.jackson.databind.ObjectMapper;
import game.vt.silence.exceptions.VTCharacterNotFoundException;
import game.vt.silence.exceptions.VTCharacterNameOccupiedException;
import game.vt.silence.exceptions.VTCharacterValueNotFoundException;
import game.vt.silence.game_mech.api.char_.DTO.*;
import game.vt.silence.game_mech.model.VTCharacter;
import game.vt.silence.game_mech.service.VTCharacterService;
import game.vt.silence.security.model.VTUser;
import game.vt.silence.security.service.SecurityService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VTCharacterController {

    @Autowired
    ObjectMapper jackson;
    @Autowired
    VTCharacterService characterService;
    @Autowired
    SecurityService securityService;

    @SneakyThrows
    @PostMapping("/char/create")
    public CharCreateRS charCreate(@RequestBody CharCreateRQ request) {
        try {
            characterService.createVTCharacter(request.getCharname());
        } catch (VTCharacterValueNotFoundException e) {
            return new CharCreateRS("wrong charname", e.getWrongName());
        } catch (VTCharacterNameOccupiedException e) {
            return new CharCreateRS("charnamealreadyused", "sometext");
        }

        VTUser vtUser = securityService.findLoggedInVT_User();
        VTCharacter vtCharacter = characterService.getVTCharacterByName(request.getCharname());

        characterService.addVTCharacter(vtUser, vtCharacter);

        return new CharCreateRS("createsuccess",
                "user: " + vtUser.getUsername()
                        + " has create character: " + vtCharacter.getCharname());
    }

    @SneakyThrows
    @PostMapping("/char")
    public String charFind(@RequestBody CharRQ request) {
        VTCharacter vtCharacter = null;

        try {
            vtCharacter = characterService.getVTCharacterByName(request.getCharname());
        } catch (VTCharacterNotFoundException e) {
            return jackson.writeValueAsString(new CharRS("wrong charname", "character not found"));
        }

        return jackson.writeValueAsString(vtCharacter);
    }


    @SneakyThrows
    @PostMapping("/char/list")
    public String charList(@RequestBody CharCreateRQ request) {

        return "text";
    }


}
