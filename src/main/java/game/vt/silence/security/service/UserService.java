package game.vt.silence.security.service;

import game.vt.silence.security.model.VT_User;

public interface UserService {

    void save(VT_User user);

    VT_User findByUsername(String username);

}
