package space.space.services.services;

import space.space.data.models.Planet;
import space.space.data.models.User;
import space.space.services.models.planets.PlanetServiceModel;

import java.util.List;

public interface PlanetService {

    Planet create(String name,User user);

    PlanetServiceModel getByName(String planetName);
    PlanetServiceModel getById(long id);

    PlanetServiceModel getByUsername(String username);

    List<PlanetServiceModel> getPlanetsToTravel(String planetName);

    void travelToPlanet(String myPlanet, String otherPlanet) throws Exception;

    void sizeUpPlanet(Planet planet) throws Exception;
    void levelUpPlanet(Planet planet) throws Exception;

    boolean checkBalance(String username, long baseCost) throws Exception;

    void buySizeUp(String username) throws Exception;
    void buyLevelUp(String username) throws Exception;


    boolean areTherePlanets();
}
