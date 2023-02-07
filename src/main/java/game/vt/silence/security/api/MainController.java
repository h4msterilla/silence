package game.vt.silence.security.api;

import game.vt.silence.game_mech.model.VTCharacter;
import game.vt.silence.game_mech.service.VTCharacterServiceImpl;
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
    VTCharacterServiceImpl service;

    @SneakyThrows
    @GetMapping("/s")
    public String forAuthUserPage(){
        String s = "text: ";
        VTCharacter character = new VTCharacter();


        //s=s+String.valueOf(a);


        return s;
    }

}
