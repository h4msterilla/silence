package game.vt.silence.security.service;

import game.vt.silence.game_mech.model.VT_Character;
import game.vt.silence.security.model.VT_User;
import game.vt.silence.security.model.VT_UserNotFoundException;
import game.vt.silence.security.repo.VT_UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class VT_UserServiceImpl implements VT_UserService {

    @Autowired
    VT_UserRepo userRepo;

    @Autowired
    BCryptPasswordEncoder encoder;

    @Override
    public void save(VT_User user) {
        if (user.getPassword() != null)
            user.setEncodedPassword(encoder.encode(user.getPassword()));
        userRepo.save(user);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepo.existsByUsername(username);
    }

    @Override
    public VT_User findByUsername(String username) throws VT_UserNotFoundException {
        if (!userRepo.existsByUsername(username)) throw new VT_UserNotFoundException();
        return userRepo.findByUsername(username);
    }

    @Override
    public void addVT_Character(VT_User user, VT_Character character) {
        user.addVT_Character(character);
        userRepo.save(user);
    }
}
