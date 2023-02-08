package game.vt.silence.game_mech.repo;

import game.vt.silence.game_mech.model.VTCharacter;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VTCharacterRepo extends CrudRepository<VTCharacter, Long> {

    VTCharacter findByCharname(String charname);

    boolean existsByCharname(String charname);

    @Query(value = "SELECT charname FROM vtcharacter",
    nativeQuery = true)
    List<String> findAllCharname();
}
