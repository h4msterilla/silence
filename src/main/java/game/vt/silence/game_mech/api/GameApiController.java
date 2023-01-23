package game.vt.silence.game_mech.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import game.vt.silence.game_mech.api.json.Char_Find;
import game.vt.silence.game_mech.model.VT_Character;
import game.vt.silence.game_mech.service.VT_CharacterService;
import game.vt.silence.game_mech.api.json.Char_Create;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameApiController {

    ObjectMapper jackson = new ObjectMapper();
    @Autowired
    VT_CharacterService characterService;

    @SneakyThrows
    @PostMapping("/char/create")
    public String createChar(@RequestBody String json){
        Char_Create char_create = jackson.readValue(json, Char_Create.class);
        //System.out.println(json);

        characterService.createCharacter(char_create.getCharname());

        return "new VT Character";
    }

    @SneakyThrows
    @PostMapping("/char")
    public String getChar(@RequestBody String json){
        Char_Find char_find =jackson.readValue(json, Char_Find.class);

        VT_Character character = characterService.getVT_CharacterByName(char_find.getCharname());

        if (character==null)System.out.println("*******");

        String respond = jackson.writeValueAsString(character);
        System.out.println(respond);
        return respond;
    }



}
