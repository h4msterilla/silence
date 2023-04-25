package game.vt.silence.game_mech.service.transactions.impl;

import game.vt.silence.game_mech.model.VTCharacter;
import game.vt.silence.game_mech.repo.VTCharacterRepo;
import game.vt.silence.game_mech.service.transactions.VTCharacterRetireService;
import game.vt.silence.security.model.VTUser;
import game.vt.silence.security.repo.VTUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VTCharacterRetireServiceImpl implements VTCharacterRetireService {

    @Autowired
    VTUserRepo vtUserRepo;
    @Autowired
    VTCharacterRepo vtCharacterRepo;

    @Override
    public void retire4vaadin(VTUser vtUser, List<VTCharacter> vtCharacterList, VTCharacter vtCharacter) {

        vtCharacterList.remove(vtCharacter);
        vtCharacter.setVtUser(null);

        vtCharacterRepo.save(vtCharacter);
        vtUserRepo.save(vtUser);

    }
}
