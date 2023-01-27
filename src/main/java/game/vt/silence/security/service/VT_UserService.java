package game.vt.silence.security.service;

import game.vt.silence.game_mech.model.VT_Character;
import game.vt.silence.security.model.VT_User;

public interface VT_UserService {

    void save(VT_User user);

    boolean existsByUsername(String username);

    VT_User findByUsername(String username);

    void addVT_Character(VT_User user, VT_Character character);

}
