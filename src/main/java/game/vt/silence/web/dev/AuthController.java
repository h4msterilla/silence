package game.vt.silence.web.dev;

import com.fasterxml.jackson.databind.ObjectMapper;
import game.vt.silence.security.model.VT_User;
import game.vt.silence.security.service.SecurityService;
import game.vt.silence.security.service.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
public class AuthController {

    @SneakyThrows
    @GetMapping("/login")
    public byte[] login(){
        File f = ResourceUtils.getFile("classpath:templates/login.html");
        return Files.readAllBytes(Path.of(f.getPath()));
    }

    @SneakyThrows
    @GetMapping(value = "/js/login.js", produces = "application/javascript; charset=UTF-8")
    public byte[] login_js(){
        File f = ResourceUtils.getFile("classpath:js/login.js");
        return Files.readAllBytes(Path.of(f.getPath()));
    }

    ObjectMapper jackson = new ObjectMapper();
    @Autowired
    UserService userService;
    @Autowired
    SecurityService securityService;

    @SneakyThrows
    @PostMapping("/login")
    public String login_in(@RequestBody String json, HttpServletResponse response){
        VT_User user_login = jackson.readValue(json, VT_User.class);

        if(userService.findByUsername(user_login.getUsername())==null) return "wrong username";

        boolean isAuth = securityService.autoLogin(user_login.getUsername(), user_login.getPassword(), response);

        if(isAuth)
            return "OK";
        else
            return "wrong password";
        //return null;
    }

}
