package space.space.services.factories.impl;

import org.junit.jupiter.api.Test;
import space.space.data.models.CreditAccount;
import space.space.data.models.CreditType;
import space.space.data.models.Planet;
import space.space.services.factories.CreditAccountFactory;
import space.space.services.factories.PlanetFactory;
import space.space.services.services.PlanetService;

import static org.junit.jupiter.api.Assertions.*;

class PlanetFactoryImplTest {

    @Test
    void checkIfCreateWorksProperly() {
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