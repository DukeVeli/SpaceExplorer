package space.space.services.services;

import space.space.data.models.Planet;
import space.space.data.models.User;
import space.space.services.models.planets.PlanetCreateServiceModel;
import space.space.services.models.planets.PlanetDetailsServiceModel;
import space.space.services.models.planets.PlanetServiceModel;

import java.util.List;

public interface PlanetService {

    Planet create(String name,User user);

    PlanetServiceModel getByName(String planetName);

    PlanetServiceModel getByUsername(String username);

    List<PlanetServiceModel> getPlanetsToTravel(String planetName);

    void travelToPlanet(String myPlanet, String otherPlanet);

    void sizeUpPlanet(Planet planet) throws Exception;
    void levelUpPlanet(Planet planet) throws Exception;

    boolean checkBalance(String username, double baseCost);

    void buySizeUp(String username) throws Exception;
    void buylevelUp(String username) throws Exception;


    boolean areTherePlanets();
}
