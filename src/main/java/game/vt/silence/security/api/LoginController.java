package game.vt.silence.security.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import game.vt.silence.security.api.json.Login_RQ;
import game.vt.silence.security.api.json.Login_RS;
import game.vt.silence.exceptions.VTUserNotFoundException;
import game.vt.silence.exceptions.VTUserWrongPasswordException;
import game.vt.silence.security.service.SecurityService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class LoginController {

    @Autowired
    ObjectMapper jackson;
    @Autowired
    SecurityService securityService;

    @SneakyThrows
    @PostMapping("/login")
    public String login(@RequestBody String json, HttpServletResponse httpServletResponse){
        Login_RQ request = jackson.readValue(json,Login_RQ.class);

        try {
            securityService.autoLogin(request.getUsername(),request.getPassword(), httpServletResponse);
        } catch (VTUserNotFoundException e) {
            return jackson.writeValueAsString(new Login_RS("wrong username","username not found"));
        } catch (VTUserWrongPasswordException e) {
            return  jackson.writeValueAsString(new Login_RS("wrong password","some text"));
        }

        return jackson.writeValueAsString(new Login_RS("login success","some text"));
    }
}
