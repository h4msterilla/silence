package game.vt.silence.security.service;

import game.vt.silence.game_mech.model.VTCharacter;
import game.vt.silence.security.model.VTUser;
import game.vt.silence.exceptions.VTUserNotFoundException;

import java.util.List;

public interface VTUserService {

    void save(VTUser user);

    boolean existsByUsername(String username);

    VTUser findByUsername(String username) throws VTUserNotFoundException;

    void addVT_Character(VTUser user, VTCharacter character);

    List<String> getVT_CharactersByUsername(String username);

}
