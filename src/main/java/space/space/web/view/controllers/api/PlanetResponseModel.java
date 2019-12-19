package space.space.web.view.controllers.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import space.space.data.models.PlanetSize;
import space.space.data.models.PlanetUpgrades;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlanetResponseModel {
    private long id;
    private String name;
    private PlanetSize size;
    private PlanetUpgrades upgrades;
    private long incomePerTrip;
    private long rating;
}
