package game.vt.silence.security.api;

import game.vt.silence.exceptions.handlers.StatusRS;
import game.vt.silence.exceptions.handlers.StatusType;
import game.vt.silence.security.api.json.Login_RQ;
import game.vt.silence.security.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class LoginController {

    @Autowired
    SecurityService securityService;

    @PostMapping("/login")
    public StatusRS login(@RequestBody Login_RQ request, HttpServletResponse httpServletResponse) {
        securityService.autoLogin(request.getUsername(), request.getPassword(), httpServletResponse);

        return new StatusRS(StatusType.SUCCESS, "login success");
    }
}
