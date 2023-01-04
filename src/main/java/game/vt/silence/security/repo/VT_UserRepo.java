package game.vt.silence.security.repo;

import game.vt.silence.security.model.VT_User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VT_UserRepo extends CrudRepository<VT_User, Long> {

        boolean existsByUsername(String username);

        VT_User findByUsername(String username);
}
