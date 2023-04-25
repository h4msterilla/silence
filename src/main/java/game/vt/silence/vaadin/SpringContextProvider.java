package game.vt.silence.vaadin;

import game.vt.silence.game_mech.service.VTCharacterValueService;
import game.vt.silence.game_mech.service.transactions.VTCharacterCreationService;
import game.vt.silence.game_mech.service.transactions.VTCharacterListProviderService;
import game.vt.silence.game_mech.service.transactions.VTCharacterRetireService;
import game.vt.silence.game_mech.service.transactions.VTCharacterValueEditionService;
import game.vt.silence.security.service.SecurityService;
import game.vt.silence.security.service.VTUserService;
import org.springframework.stereotype.Component;

@Component
public class SpringContextProvider {

    private static SecurityService securityService;
    private static VTUserService vtUserService;
    private static VTCharacterListProviderService vtCharacterListProviderService;
    private static VTCharacterValueService vtCharacterValueService;
    private static VTCharacterValueEditionService vtCharacterValueEditionService;
    private static VTCharacterCreationService vtCharacterCreationService;
    private static VTCharacterRetireService vtCharacterRetireService;

    public SpringContextProvider(SecurityService securityService,
                                 VTUserService vtUserService,
                                 VTCharacterListProviderService vtCharacterListProviderService,
                                 VTCharacterValueService vtCharacterValueService,
                                 VTCharacterValueEditionService vtCharacterValueEditionService,
                                 VTCharacterCreationService vtCharacterCreationService,
                                 VTCharacterRetireService vtCharacterRetireService) {
        this.securityService = securityService;
        this.vtUserService = vtUserService;
        this.vtCharacterListProviderService = vtCharacterListProviderService;
        this.vtCharacterValueService = vtCharacterValueService;
        this.vtCharacterValueEditionService = vtCharacterValueEditionService;
        this.vtCharacterCreationService = vtCharacterCreationService;
        this.vtCharacterRetireService = vtCharacterRetireService;
    }

    public static SecurityService getSecurityService() {
        return securityService;
    }

    public static VTUserService getVtUserService() {
        return vtUserService;
    }

    public static VTCharacterListProviderService getVtCharacterListProviderService() {
        return vtCharacterListProviderService;
    }

    public static VTCharacterValueService getVtCharacterValueService() {
        return vtCharacterValueService;
    }

    public static VTCharacterValueEditionService getVtCharacterValueEditionService() {
        return vtCharacterValueEditionService;
    }

    public static VTCharacterCreationService getVtCharacterCreationService(){
        return vtCharacterCreationService;
    }

    public static VTCharacterRetireService getVtCharacterRetireService(){
        return vtCharacterRetireService;
    }
}
