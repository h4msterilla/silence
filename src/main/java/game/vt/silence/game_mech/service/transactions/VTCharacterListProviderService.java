package game.vt.silence.game_mech.service.transactions;

import game.vt.silence.game_mech.model.VTCharacter;
import game.vt.silence.security.model.VTUser;

import java.util.List;

public interface VTCharacterListProviderService {

    List<VTCharacter> getAllVTCharacterList();

    List<VTCharacter> getVTCharacterListByVTUser(VTUser vtUser);

    List<VTCharacter> getVTCharacterListByLogInUser();

}
