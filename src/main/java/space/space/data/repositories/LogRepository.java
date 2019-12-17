package space.space.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import space.space.data.models.Log;
import java.util.Optional;

@Repository
public interface LogRepository extends JpaRepository<Log, String> {

    Optional<Log> findLogByUsername(String username);

}
