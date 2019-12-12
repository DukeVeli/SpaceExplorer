package space.space.services.factories.impl;

import space.space.config.annotations.Factory;
import space.space.data.models.Planet;
import space.space.data.models.PlanetSize;
import space.space.data.models.PlanetUpgrades;
import space.space.services.factories.PlanetFactory;

import static space.space.services.factories.Constants.INITIAL_INCOME_PER_TRIP;


@Factory
public class PlanetFactoryImpl implements PlanetFactory {
    @Override
    public Planet create(String name) {
        Planet planet = new Planet();
        planet.setName(name);
        planet.setSize(PlanetSize.SMALL);
        planet.setUpgrades(PlanetUpgrades.EMPTY);
        planet.setIncomePerTrip(INITIAL_INCOME_PER_TRIP);

        return planet;
    }
}
