package game.vt.silence.game_mech.service.transactions;

import game.vt.silence.game_mech.model.VTCharacter;
import game.vt.silence.security.model.VTUser;

import java.util.List;

public interface VTCharacterRetireService {

    void retire4vaadin(VTUser vtUser, List<VTCharacter> vtCharacterList, VTCharacter vtCharacter);

}
