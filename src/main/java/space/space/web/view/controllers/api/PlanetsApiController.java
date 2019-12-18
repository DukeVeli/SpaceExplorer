package space.space.web.view.controllers.api;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import space.space.services.models.planets.PlanetServiceModel;
import space.space.services.services.PlanetService;
import space.space.web.base.BaseController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class PlanetsApiController extends BaseController {
    private final PlanetService planetService;
    private final ModelMapper mapper;

    @GetMapping(value = "/api/planet")
    public ResponseEntity<PlanetResponseModel> getPlanet(HttpSession session) {
        String username = getUsername(session);
        PlanetServiceModel planet = planetService.getByUsername(username);
        PlanetResponseModel result = mapper.map(planet, PlanetResponseModel.class);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/api/planet-all")
    public List<PlanetResponseModel> getPlanets() {
        return planetService.getAll()
                .stream()
                .map(planet -> mapper.map(planet, PlanetResponseModel.class))
                .collect(Collectors.toList());
    }

    @PostMapping("/api/planet/add-rating-user/{id}")
    public void ratePlanet(@PathVariable long id, HttpSession session, HttpServletResponse response) throws IOException {
        String username = getUsername(session);
        planetService.rateUpPlanet(username);

        response.sendRedirect("/planet/travel/");
    }

}
