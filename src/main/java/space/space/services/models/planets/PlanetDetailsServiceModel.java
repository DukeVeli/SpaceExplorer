package space.space.services.models.planets;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import space.space.data.models.Planet;
import space.space.data.models.PlanetSize;
import space.space.data.models.PlanetUpgrades;

@Getter
@Setter
@NoArgsConstructor
public class PlanetDetailsServiceModel {
    private String name;
    private Planet planet;
    private PlanetSize size;
    private PlanetUpgrades upgrades;
    private int incomePerTrip;

}
