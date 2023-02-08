package game.vt.silence.game_mech.service;

import game.vt.silence.game_mech.model.VTCharacterValue;
import game.vt.silence.game_mech.repo.VTCharacterValueRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VTCharacterValueServiceImpl implements VTCharacterValueService {

    @Autowired
    VTCharacterValueRepo vtCharacterValueRepo;

    @Override
    public void save(VTCharacterValue vtCharacterValue) {
        vtCharacterValueRepo.save(vtCharacterValue);
    }

}
