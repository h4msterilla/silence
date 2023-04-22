package game.vt.silence.vaadin;

import game.vt.silence.game_mech.service.transactions.VTCharacterListProviderService;
import game.vt.silence.security.service.SecurityService;
import game.vt.silence.security.service.VTUserService;
import org.springframework.stereotype.Component;

@Component
public class SpringContextProvider {

    private static SecurityService securityService;
    private static VTUserService vtUserService;
    private static VTCharacterListProviderService vtCharacterListProviderService;

    public SpringContextProvider(SecurityService securityService,
                                 VTUserService vtUserService,
                                 VTCharacterListProviderService vtCharacterListProviderService) {
        this.securityService = securityService;
        this.vtUserService = vtUserService;
        this.vtCharacterListProviderService = vtCharacterListProviderService;
    }

    public static SecurityService getSecurityService() {
        return securityService;
    }

    public static VTUserService getVtUserService(){
        return vtUserService;
    }

    public static VTCharacterListProviderService getVtCharacterListProviderService(){
        return vtCharacterListProviderService;
    }

}
