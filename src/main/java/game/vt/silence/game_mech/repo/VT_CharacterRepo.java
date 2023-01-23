package game.vt.silence.game_mech.repo;

import game.vt.silence.game_mech.model.VT_Character;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VT_CharacterRepo extends CrudRepository<VT_Character, Long> {

    @Query(value = "SELECT * FROM vt_character WHERE value_name = :value_name",
    nativeQuery = true)
    VT_Character findByValue_name(@Param("value_name") String value_name);

}
