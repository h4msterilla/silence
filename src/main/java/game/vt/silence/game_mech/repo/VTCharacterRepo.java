package game.vt.silence.game_mech.repo;

import game.vt.silence.game_mech.model.VTCharacter;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VTCharacterRepo extends CrudRepository<VTCharacter, Long> {

    @Query(value = "SELECT * FROM vt_character WHERE value_name = :value_name",
    nativeQuery = true)
    VTCharacter findByValue_name(@Param("value_name") String value_name);

    @Query(value = "SELECT EXISTS(SELECT * FROM vt_character WHERE value_name = :value_name)",
    nativeQuery = true)
    boolean existsByValue_name(@Param("value_name") String value_name);

    @Query(value = "SELECT value_name FROM vt_character",
    nativeQuery = true)
    List<String> findAllValue_name();
}
