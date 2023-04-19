package game.vt.silence.vaadin;

import game.vt.silence.security.service.SecurityService;
import org.springframework.stereotype.Component;

@Component
public class SpringContextProvider {

    private static SecurityService securityService;

    public SpringContextProvider(SecurityService securityService) {
        this.securityService = securityService;
    }

    public static SecurityService getSecurityService() {
        return securityService;
    }

}
