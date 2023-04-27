package game.vt.silence.game_mech.service.transactions;

import game.vt.silence.game_mech.model.VTCharacter;
import game.vt.silence.security.model.VTUser;

import java.util.List;

public interface VTCharacterCreationService {

    void create(String vtCharacterName, String vtUserName);

    VTCharacter create4vaadin(VTUser vtUser, List<VTCharacter> vtCharacterList);

}
