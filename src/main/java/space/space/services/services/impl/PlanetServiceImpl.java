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
import space.space.errors.NemashKintiBraatException;
import space.space.services.factories.PlanetFactory;
import space.space.services.models.planets.PlanetServiceModel;
import space.space.services.services.PlanetService;
import space.space.services.services.UsersService;

import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PlanetServiceImpl implements PlanetService {
    private final PlanetRepository planetRepository;
    private final UserRepository userRepository;
    private final PlanetFactory planetFactory;
    private final UsersService usersService;

    private final ModelMapper mapper;

    @Override
    public PlanetServiceModel getByName(String name) {
        Optional<Planet> planetResult = planetRepository.getByNameIgnoreCase(name);
        return getPlanetServiceModel(planetResult);
    }

    @Override
    public PlanetServiceModel getById(long id) {
        Optional<Planet> planetResult = planetRepository.getById(id);
        return getPlanetServiceModel(planetResult);
    }

    private PlanetServiceModel getPlanetServiceModel(Optional<Planet> planetResult) {
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
        Planet planet = planetFactory.create(name);
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
    public void travelToPlanet(String myPlanetName, String otherPlanetName) throws Exception {
        Planet otherPlanet = planetRepository.getByNameIgnoreCase(otherPlanetName).get();
        Planet myPlanet = planetRepository.getByNameIgnoreCase(myPlanetName).get();
        User otherUser = userRepository.findByPlanetName(otherPlanetName);
        User myUser = userRepository.findByPlanetName(myPlanetName);

        long moneyPerTravel = otherPlanet.getIncomePerTrip();
        long myMoney = myUser.getCreditAccount().getCreditAmount();
        long hisMoney = otherUser.getCreditAccount().getCreditAmount();
        if (myMoney<moneyPerTravel){throw new NemashKintiBraatException("Nemash kinti braat!");}

        otherPlanet.setRating(otherPlanet.getRating()+1);
        otherPlanet.setIncomePerTrip(otherPlanet.getIncomePerTrip()+1);

        myPlanet.setRating(myPlanet.getRating()+1);
        myPlanet.setIncomePerTrip(myPlanet.getIncomePerTrip()+1);

        otherUser.getCreditAccount().setCreditAmount(hisMoney+moneyPerTravel);
        myUser.getCreditAccount().setCreditAmount(myMoney-moneyPerTravel);

        planetRepository.saveAndFlush(myPlanet);
        planetRepository.saveAndFlush(otherPlanet);
        userRepository.saveAndFlush(myUser);
        userRepository.saveAndFlush(otherUser);

    }




    @Override
    public boolean checkBalance(String username, long baseCost) {
        User user = userRepository.findByUsername(username);
        return (user.getCreditAccount().getCreditAmount() >= baseCost);
    }

    @Override
    public void sizeUpPlanet(Planet planet) {
        if (planet.getSize().equals(PlanetSize.ENORMOUS))return;

        if (planet.getSize().equals(PlanetSize.HUGE)) {
            planet.setSize(PlanetSize.ENORMOUS);
            planet.setIncomePerTrip(planet.getIncomePerTrip() + 800);
        } else if (planet.getSize().equals(PlanetSize.LARGE)) {
            planet.setSize(PlanetSize.HUGE);
            planet.setIncomePerTrip(planet.getIncomePerTrip() + 400);
        } else if (planet.getSize().equals(PlanetSize.MEDIUM)) {
            planet.setSize(PlanetSize.LARGE);
            planet.setIncomePerTrip(planet.getIncomePerTrip() + 200);
        } else if (planet.getSize().equals(PlanetSize.SMALL)) {
            planet.setSize(PlanetSize.MEDIUM);
            planet.setIncomePerTrip(planet.getIncomePerTrip() + 100);
        }
        planetRepository.saveAndFlush(planet);
    }

    @Override
    public void buySizeUp(String username) throws Exception {
        Optional<Planet> oPlanet = planetRepository.getByUserUsername(username);
        if (oPlanet.isEmpty()) {
            throw new Exception("No Planet");
        }
        Planet planet = oPlanet.get();
        if (!checkBalance(username, 100000)) {
            throw new NemashKintiBraatException("nemash kinti braat");
        }
        sizeUpPlanet(planet);
        usersService.spendMoney(username, 100000);

    }

    @Override
    public void buyLevelUp(String username) throws Exception {
        Optional<Planet> oPlanet = planetRepository.getByUserUsername(username);
        if (oPlanet.isEmpty()) {
            throw new Exception("No Planet");
        }
        Planet planet = oPlanet.get();

        if (!checkBalance(username, 50000)) {
            throw new NemashKintiBraatException("Not enough Credits!");
        }
        levelUpPlanet(planet);
        usersService.spendMoney(username, 50000);
    }


    @Override
    public void levelUpPlanet(Planet planet) throws Exception {
        if (planet.getUpgrades().equals(PlanetUpgrades.FORTH)) return;

        if (planet.getUpgrades().equals(PlanetUpgrades.THIRD)) {
            planet.setUpgrades(PlanetUpgrades.FORTH);
            planet.setIncomePerTrip(planet.getIncomePerTrip() + 50);
        } else if (planet.getUpgrades().equals(PlanetUpgrades.SECOND)) {
            planet.setUpgrades(PlanetUpgrades.THIRD);
            planet.setIncomePerTrip(planet.getIncomePerTrip() + 50);
        } else if (planet.getUpgrades().equals(PlanetUpgrades.FIRST)) {
            planet.setUpgrades(PlanetUpgrades.SECOND);
            planet.setIncomePerTrip(planet.getIncomePerTrip() + 50);
        } else {
            planet.setUpgrades(PlanetUpgrades.FIRST);
            planet.setIncomePerTrip(planet.getIncomePerTrip() + 50);
        }
        planetRepository.saveAndFlush(planet);
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
