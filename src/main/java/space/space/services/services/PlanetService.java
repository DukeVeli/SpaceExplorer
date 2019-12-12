package space.space.services.services;

import space.space.data.models.Planet;
import space.space.data.models.User;
import space.space.services.models.planets.PlanetCreateServiceModel;
import space.space.services.models.planets.PlanetDetailsServiceModel;
import space.space.services.models.planets.PlanetServiceModel;

import java.util.List;

public interface PlanetService {

    PlanetDetailsServiceModel getByName(String name);

    Planet create(User user);

    List<PlanetServiceModel> getPlanetsToTravel(String planetName);

    void sizeUpPlanet();
    void upgradePlanet();

    PlanetServiceModel getByUsername(String username);
}
