package game.vt.silence.web.dev;

import com.fasterxml.jackson.databind.ObjectMapper;
import game.vt.silence.security.model.VT_User;
import game.vt.silence.security.service.SecurityService;
import game.vt.silence.security.service.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
public class RegController {

    @SneakyThrows
    @GetMapping("/reg")
    public byte[] reg(){
        File f = ResourceUtils.getFile("classpath:templates/reg.html");
        return Files.readAllBytes(Path.of(f.getPath()));
    }

    @SneakyThrows
    @GetMapping(value = "/js/reg.js", produces = "application/javascript; charset=UTF-8")
    public byte[] login_js(){
        File f = ResourceUtils.getFile("classpath:js/reg.js");
        return Files.readAllBytes(Path.of(f.getPath()));
    }

    ObjectMapper jackson = new ObjectMapper();
    @Autowired
    UserService userService;
    @Autowired
    SecurityService securityService;
    @Autowired
    BCryptPasswordEncoder encoder;

    @SneakyThrows
    @PostMapping("/reg")
    public String reg_in(@RequestBody String json){
        VT_User user_reg = jackson.readValue(json, VT_User.class);

        if(userService.findByUsername(user_reg.getUsername())!=null) return "username already used";
        user_reg.setEncodedPassword(encoder.encode(user_reg.getPassword()));
        userService.save(user_reg);
        securityService.autoLogin(user_reg.getUsername(), user_reg.getPassword());

        return "OK";
    }

}
