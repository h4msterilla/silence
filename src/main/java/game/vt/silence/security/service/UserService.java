package game.vt.silence.security.service;

import game.vt.silence.security.model.VT_User;

public interface UserService {

    void save(VT_User user);

    boolean existsByUsername(String username);

    VT_User findByUsername(String username);

}
