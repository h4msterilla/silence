package game.vt.silence.security.service;

import game.vt.silence.game_mech.model.VTCharacter;
import game.vt.silence.security.model.VTUser;
import game.vt.silence.exceptions.VTUserNotFoundException;
import game.vt.silence.security.repo.VTUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VTUserServiceImpl implements VTUserService {

    @Autowired()
    VTUserRepo vt_userRepo;

    @Autowired
    BCryptPasswordEncoder encoder;

    @Override
    public void save(VTUser user) {
        if (user.getPassword() != null)
            user.setEncodedPassword(encoder.encode(user.getPassword()));
        vt_userRepo.save(user);
    }

    @Override
    public boolean existsByUsername(String username) {
        return vt_userRepo.existsByUsername(username);
    }

    @Override
    public VTUser findByUsername(String username) throws VTUserNotFoundException {
        if (!vt_userRepo.existsByUsername(username)) throw new VTUserNotFoundException();
        return vt_userRepo.findByUsername(username);
    }

    @Override
    public void addVTCharacter(VTUser user, VTCharacter character) {
        user.addVT_Character(character);
        vt_userRepo.save(user);
    }

    @Override
    public List<String> getVTCharactersByUsername(String username) {



        return null;
    }
}
