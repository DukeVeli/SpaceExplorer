package space.space.data.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import space.space.data.models.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String username);
    Optional<User> findByUsernameAndPassword(String username, String password);

    User findByUsername(String username);
    User findByPlanetName(String planetName);
}
