package game.vt.silence.game_mech.service.transactions.impl;

import game.vt.silence.exceptions.VTCharacterNameOccupiedException;
import game.vt.silence.exceptions.VTUserNotFoundException;
import game.vt.silence.game_mech.model.VTCharacter;
import game.vt.silence.game_mech.model.VTCharacterValue;
import game.vt.silence.game_mech.model.factories.VTCharacterValueListFactory;
import game.vt.silence.game_mech.repo.VTCharacterRepo;
import game.vt.silence.game_mech.repo.VTCharacterValueRepo;
import game.vt.silence.game_mech.service.transactions.VTCharacterCreationService;
import game.vt.silence.security.model.VTUser;
import game.vt.silence.security.repo.VTUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VTCharacterCreationServiceImpl implements VTCharacterCreationService {

    @Autowired
    VTCharacterRepo vtCharacterRepo;
    @Autowired
    VTUserRepo vtUserRepo;
    @Autowired
    VTCharacterValueRepo vtCharacterValueRepo;
    @Autowired
    VTCharacterValueListFactory vtCharacterValueListFactory;

    @Transactional
    public void create(String vtCharacterName, String vtUserName) {

        if (vtCharacterRepo.existsByCharname(vtCharacterName)) {
            throw new VTCharacterNameOccupiedException();
        }

        if (!vtUserRepo.existsByUsername(vtUserName)) {
            throw new VTUserNotFoundException();
        }

        List<VTCharacterValue> vtCharacterValueList = vtCharacterValueListFactory.getNewVTCharacterValueList();
        VTCharacter vtCharacter = new VTCharacter();
        VTUser vtUser = vtUserRepo.findByUsername(vtUserName);

        vtCharacter.setCharname(vtCharacterName);
        vtCharacter.setValues(vtCharacterValueList);
        vtCharacter.setVtUser(vtUser);

        vtCharacterValueList.forEach(x -> x.setVtCharacter(vtCharacter));

        vtUser.addVT_Character(vtCharacter);

        vtCharacterValueRepo.saveAll(vtCharacterValueList);
        vtCharacterRepo.save(vtCharacter);
        vtUserRepo.save(vtUser);

    }

}
