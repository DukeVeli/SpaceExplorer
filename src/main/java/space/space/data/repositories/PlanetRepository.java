package space.space.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import space.space.data.models.Planet;

import java.util.Optional;

public interface PlanetRepository extends JpaRepository<Planet, Long> {

    Optional<Planet> getByNameIgnoreCase(String name);

    Optional<Planet> getByUserUsername(String username);
    Optional<Planet> getById(long id);




}