package game.vt.silence.game_mech.service.transactions;

import game.vt.silence.game_mech.model.VTCharacter;
import game.vt.silence.game_mech.repo.VTCharacterRepo;
import game.vt.silence.security.model.VTUser;
import game.vt.silence.security.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VTCharacterListProviderServiceImpl implements VTCharacterListProviderService {

    @Autowired
    VTCharacterRepo repo;
    @Autowired
    SecurityService securityService;

    @Override
    public List<VTCharacter> getAllVTCharacterList() {
        return repo.findAll();
    }

    @Override
    public List<VTCharacter> getVTCharacterListByVTUser(VTUser vtUser) {
        return repo.findByVtUser(vtUser);
    }

    @Override
    public List<VTCharacter> getVTCharacterListByLogInUser() {
        return repo.findByVtUser(securityService.findLoggedInVT_User());
    }
}
