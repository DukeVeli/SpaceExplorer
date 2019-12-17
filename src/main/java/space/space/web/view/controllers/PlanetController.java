package space.space.web.view.controllers;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import space.space.services.models.planets.PlanetServiceModel;
import space.space.services.services.PlanetService;
import space.space.web.base.BaseController;
import space.space.web.view.models.PlanetViewModel;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/planet")
@AllArgsConstructor
public class PlanetController extends BaseController {
    private final PlanetService planetService;
    private final ModelMapper mapper;


    @GetMapping("/travel")
    public ModelAndView getTravel(ModelAndView modelAndView, HttpSession session) {
        boolean condition = planetService.areTherePlanets();
        List<PlanetServiceModel> planets = planetService.getPlanetsToTravel(getPlanetName(session))
                .stream()
                .map(planet -> mapper.map(planet, PlanetServiceModel.class))
                .collect(Collectors.toList());
        modelAndView.addObject("condition", condition);
        modelAndView.addObject("planets", planets);
        return modelAndView;
    }

    @GetMapping("/travel/{planetId}")
    public ModelAndView getTravel(@PathVariable long planetId, ModelAndView modelAndView) {
        PlanetServiceModel serviceModel = planetService.getById(planetId);
        PlanetViewModel viewModel = mapper.map(serviceModel, PlanetViewModel.class);
        modelAndView.addObject("planet", viewModel);
        modelAndView.setViewName("/planet/guest_planet");
        return modelAndView;
    }

    @GetMapping("/travel/trip/{planetToTravel}")
    public String trip(@PathVariable String planetToTravel, HttpSession session) throws Exception {
        planetService.travelToPlanet(getPlanetName(session),planetToTravel);
        return "redirect:/planet/travel";
    }

    @GetMapping("/my_planet")
    public String getMyPlanet() {
        return "/planet/my_planet";
    }

    @GetMapping("/my_planet/sizeUp")
    public String sizeUp(HttpSession session) throws Exception {
        planetService.buySizeUp(getUsername(session));
        return "redirect:/planet/my_planet";
    }

    @GetMapping("/my_planet/levelUp")
    public String levelUp(HttpSession session) throws Exception {
        planetService.buyLevelUp(getUsername(session));
        return "redirect:/planet/my_planet";
    }
}
