package game.vt.silence.game_mech.repo;

import game.vt.silence.game_mech.model.VTCharacter;
import game.vt.silence.game_mech.model.VTCharacterValue;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VTCharacterValueRepo extends CrudRepository<VTCharacterValue, Long> {

    List<VTCharacterValue> findByVtCharacter(VTCharacter vtCharacter);

}
