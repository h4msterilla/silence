package game.vt.silence.security.api;

import game.vt.silence.game_mech.model.VT_Character;
import game.vt.silence.game_mech.service.VT_CharacterServiceImpl;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/")
    public String main(){
        return "hello";
    }

    @Autowired
    VT_CharacterServiceImpl service;

    @SneakyThrows
    @GetMapping("/s")
    public String forAuthUserPage(){
        String s = "text: ";
        VT_Character character = new VT_Character();


        //s=s+String.valueOf(a);


        return s;
    }

}
