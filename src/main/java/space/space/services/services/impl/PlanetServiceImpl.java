package space.space.services.services.impl;


import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import space.space.data.models.Planet;
import space.space.data.models.PlanetSize;
import space.space.data.models.PlanetUpgrades;
import space.space.data.models.User;
import space.space.data.repositories.PlanetRepository;
import space.space.data.repositories.UserRepository;
import space.space.services.factories.PlanetFactory;
import space.space.services.models.planets.PlanetServiceModel;
import space.space.services.services.PlanetService;
import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PlanetServiceImpl implements PlanetService {
    private final PlanetRepository planetRepository;
    private final UserRepository userRepository;
    private final PlanetFactory planetFactory;
    private final ModelMapper mapper;

    @Override
    public PlanetServiceModel getByName(String name) {
        Optional<Planet> planetResult = planetRepository.getByNameIgnoreCase(name);
        if (planetResult.isEmpty()) {
            try {
                throw new Exception("Planet with such name does not exist");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Planet planet = planetResult.get();

        PlanetServiceModel serviceModel = mapper.map(planet, PlanetServiceModel.class);
                serviceModel.setIncomePerTrip(planet.getIncomePerTrip());
                serviceModel.setName(planet.getName());
                serviceModel.setUpgrades(planet.getUpgrades());
                serviceModel.setSize(planet.getSize());
        return serviceModel;
    }



    @Override
    public Planet create(String name, User user) {
        Planet  planet = planetFactory.create(name);
        planet.setUser(user);
        return planetRepository.saveAndFlush(planet);

    }

    @Override
    public List<PlanetServiceModel> getPlanetsToTravel(String planetName) {
        return planetRepository.findAll()
                .stream()
                .filter(planet -> !planet.getName().equals(planetName))
                .map(planet -> mapper.map(planet, PlanetServiceModel.class))
                .collect(Collectors.toList());
    }


    @Override
    public void travelToPlanet(String myPlanet, String otherPlanet) {
        Planet foreign = planetRepository.getByNameIgnoreCase(otherPlanet).get();
        Planet own = planetRepository.getByNameIgnoreCase(myPlanet).get();
        User other = userRepository.findByPlanetName(otherPlanet);
        User me = userRepository.findByPlanetName(myPlanet);



    }

    @Override
    public void sizeUpPlanet(String name) {
        Planet planet = planetRepository.getByUserUsername(name).get();
        if (planet.getSize().equals(PlanetSize.ENORMOUS)){
            return;
        }else {
            if (planet.getSize().equals(PlanetSize.HUGE)){
                planet.setSize(PlanetSize.ENORMOUS);
                planet.setIncomePerTrip(planet.getIncomePerTrip()+800);
            }else if (planet.getSize().equals(PlanetSize.LARGE)) {
                planet.setSize(PlanetSize.HUGE);
                planet.setIncomePerTrip(planet.getIncomePerTrip()+400);
            }else if (planet.getSize().equals(PlanetSize.MEDIUM)) {
                planet.setSize(PlanetSize.LARGE);
                planet.setIncomePerTrip(planet.getIncomePerTrip()+200);
            }else if (planet.getSize().equals(PlanetSize.SMALL)) {
                planet.setSize(PlanetSize.MEDIUM);
                planet.setIncomePerTrip(planet.getIncomePerTrip()+100);
            }
        }

        planetRepository.save(planet);

    }

    @Override
    public void levelUpPlanet(String name) {
        Planet planet = planetRepository.getByUserUsername(name).get();
        if (planet.getUpgrades().equals(PlanetUpgrades.FORTH)){
            return;
        }else {
            if (planet.getUpgrades().equals(PlanetUpgrades.THIRD)){
                planet.setUpgrades(PlanetUpgrades.FORTH);
                planet.setIncomePerTrip(planet.getIncomePerTrip()+50);
            }else if (planet.getUpgrades().equals(PlanetUpgrades.SECOND)){
                planet.setUpgrades(PlanetUpgrades.THIRD);
                planet.setIncomePerTrip(planet.getIncomePerTrip()+50);
            }else if (planet.getUpgrades().equals(PlanetUpgrades.FIRST)){
                planet.setUpgrades(PlanetUpgrades.SECOND);
                planet.setIncomePerTrip(planet.getIncomePerTrip()+50);
            }else if (planet.getUpgrades().equals(PlanetUpgrades.EMPTY)){
                planet.setUpgrades(PlanetUpgrades.FIRST);
                planet.setIncomePerTrip(planet.getIncomePerTrip()+50);
            }
        }
        planetRepository.save(planet);
    }

    @Override
    public PlanetServiceModel getByUsername(String username) {
        Optional<Planet> planet = planetRepository.getByUserUsername(username);
        if (planet.isEmpty()) {
            try {
                throw new Exception("User have no planet");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return mapper.map(planet.get(), PlanetServiceModel.class);
    }

    @Override
    public boolean areTherePlanets() {
        return planetRepository.count() > 1;
    }
}
