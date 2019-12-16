package space.space.web.view.controllers;


import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import space.space.services.models.planets.PlanetServiceModel;
import space.space.services.services.PlanetService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/planet")
@AllArgsConstructor
public class PlanetController {
    private final PlanetService planetService;
    private final ModelMapper mapper;


    @GetMapping("/travel")
    public ModelAndView getTravel(ModelAndView modelAndView) {
        boolean condition = planetService.areTherePlanets();
        List<PlanetServiceModel> planets = planetService.getPlanetsToTravel(getMyPlanet())
                    .stream()
                    .map(planet -> mapper.map(planet, PlanetServiceModel.class))
                    .collect(Collectors.toList());
        modelAndView.addObject("condition", condition);
        modelAndView.addObject("planets", planets);

        return modelAndView;
    }

    @GetMapping("/my_planet")
    public String getMyPlanet() {
        return "/planet/my_planet";
    }

    @GetMapping("/my_planet/sizeUp")
    public String sizeUp (){
        double moneyActual=0;
        double cost=50000;
        String name = "";
        planetService.sizeUpPlanet(name);

        return "/planet/my_planet";
    }

    @GetMapping("/my_planet/levelUp")
    public String levelUp (){
        double moneyActual=0;
        double cost=50000;
        String name = "";
        planetService.levelUpPlanet(name);


        return "/planet/my_planet";
    }
}
