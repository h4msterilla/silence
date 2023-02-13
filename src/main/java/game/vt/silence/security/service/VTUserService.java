package game.vt.silence.security.service;

import game.vt.silence.game_mech.model.VTCharacter;
import game.vt.silence.security.model.VTUser;

import java.util.List;

public interface VTUserService {

    void save(VTUser user);

    boolean existsByUsername(String username);

    VTUser findByUsername(String username);

    void addVTCharacter(VTUser user, VTCharacter character);

    List<String> getVTCharactersByUsername(String username);

}
