package space.space.services.services.impl;

import org.junit.jupiter.api.Test;
import space.space.data.models.Planet;
import space.space.services.factories.PlanetFactory;
import space.space.services.factories.impl.PlanetFactoryImpl;

import static org.junit.jupiter.api.Assertions.*;

class PlanetServiceImplTest {

    @Test
    void createWorksProperly() {
        PlanetFactory factory = new PlanetFactoryImpl();
        Planet planet = new Planet();
        planet.setIncomePerTrip(100);
        planet.setRating(0);
        planet.setId(1);
        planet.setName("Pesho");

        Planet toCheck = factory.create("Pesho");
        assertEquals(planet.getIncomePerTrip(),toCheck.getIncomePerTrip());
        assertEquals(planet.getName(),toCheck.getName());
    }
}