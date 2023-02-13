package game.vt.silence.security.api;

import game.vt.silence.exceptions.handlers.StatusRS;
import game.vt.silence.exceptions.handlers.StatusType;
import game.vt.silence.security.api.json.Reg_RQ;
import game.vt.silence.security.service.SecurityService;
import game.vt.silence.security.validation.PasswordValidator;
import game.vt.silence.security.validation.UsernameValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class RegController {

    @Autowired
    SecurityService securityService;

    @PostMapping("/reg")
    public StatusRS reg(@RequestBody Reg_RQ request, HttpServletResponse httpServletResponse) {

        UsernameValidator.validate(request.getUsername());
        PasswordValidator.validate(request.getPassword());

        securityService.regUser(request.getUsername(), request.getPassword());

        securityService.autoLogin(request.getUsername(), request.getPassword(), httpServletResponse);

        return new StatusRS(StatusType.SUCCESS, "register success");
    }


}
