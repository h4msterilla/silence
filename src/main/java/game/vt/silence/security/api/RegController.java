package game.vt.silence.security.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import game.vt.silence.security.api.json.Reg_RQ;
import game.vt.silence.security.api.json.Reg_RS;
import game.vt.silence.security.model.VT_UserUsernameOccupiedException;
import game.vt.silence.security.service.SecurityService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class RegController {

    @Autowired
    ObjectMapper jackson;
    @Autowired
    SecurityService securityService;

    @SneakyThrows
    @PostMapping("/reg")
    public String reg(@RequestBody String json, HttpServletResponse httpServletResponse){
        Reg_RQ request = jackson.readValue(json, Reg_RQ.class);

        try {
            securityService.regUser(request.getUsername(),request.getPassword());
        } catch (VT_UserUsernameOccupiedException e) {
            return jackson.writeValueAsString(new Reg_RS("username already used","some text"));
        }

        securityService.autoLogin(request.getUsername(),request.getPassword(),httpServletResponse);

        return jackson.writeValueAsString(new Reg_RS("reg success","some text"));
    }


}
