package game.vt.silence.security.repo;

import game.vt.silence.security.model.VTUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VTUserRepo extends CrudRepository<VTUser, Long> {

    boolean existsByUsername(String username);

    VTUser findByUsername(String username);

    @Query(value = "SELECT value_name FROM vt_character WHERE id IN " +
            "(SELECT vt_character_list_id FROM vt_user_vt_character_list WHERE vt_user_id = " +
            "(SELECT id FROM vt_user WHERE vt_username = :username))",
            nativeQuery = true)
    List<String> findCharnamesByUsername(@Param("username") String username);
}
