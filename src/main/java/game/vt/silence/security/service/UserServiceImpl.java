package game.vt.silence.security.service;

import game.vt.silence.security.model.VT_User;
import game.vt.silence.security.repo.VT_UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    VT_UserRepo userRepo;

    @Autowired
    BCryptPasswordEncoder encoder;

    @Override
    public void save(VT_User user) {
        user.setEncodedPassword(encoder.encode(user.getPassword()));

        userRepo.save(user);
    }

    @Override
    public VT_User findByUsername(String username) {
        if (userRepo.existsByUsername(username))
            return userRepo.findByUsername(username);

        return null;
    }
}
