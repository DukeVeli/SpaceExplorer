package space.space.services.services.impl;


import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import space.space.data.models.Planet;
import space.space.data.models.User;
import space.space.data.repositories.PlanetRepository;
import space.space.services.factories.PlanetFactory;
import space.space.services.models.planets.PlanetDetailsServiceModel;
import space.space.services.models.planets.PlanetServiceModel;
import space.space.services.services.PlanetService;
import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PlanetServiceImpl implements PlanetService {
    private final PlanetRepository planetRepository;
    private final PlanetFactory planetFactory;
    private final ModelMapper mapper;

    @Override
    public PlanetDetailsServiceModel getByName(String name) {
        Optional<Planet> planetResult = planetRepository.getByNameIgnoreCase(name);
      /*  if (planetResult.isEmpty()) {
            throw new PlanetNotFoundException("Hero with such name does not exist");
        }*/

        Planet planet = planetResult.get();

        PlanetDetailsServiceModel serviceModel = mapper.map(planet, PlanetDetailsServiceModel.class);
                serviceModel.setIncomePerTrip(planet.getIncomePerTrip());
                serviceModel.setName(planet.getName());
                serviceModel.setUpgrades(planet.getUpgrades());
                serviceModel.setSize(planet.getSize());
        return serviceModel;
    }



    @Override
    public Planet create(User user) {
        Planet  planet = planetFactory.create(user.getUsername());
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
    public void sizeUpPlanet() {

    }

    @Override
    public void upgradePlanet() {

    }

    @Override
    public PlanetServiceModel getByUsername(String username) {
        Optional<Planet> planet = planetRepository.getByUserUsername(username);
       /* if (planet.isEmpty()) {
            throw new Exception("Not such hero");
        }*/
        return mapper.map(planet.get(), PlanetServiceModel.class);
    }
}
