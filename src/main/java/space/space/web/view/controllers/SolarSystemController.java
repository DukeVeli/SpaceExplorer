package space.space.web.view.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/explore")
public class SolarSystemController {
    @GetMapping("/home")
    public String getSolarSystem() {
        return "explore/solar-system.html";
    }

    @GetMapping("/sun")
    public String getSun() {
        return "explore/sun.html";
    }

    @GetMapping("/mercury")
    public String getMercury () {
        return "explore/mercury.html";
    }

    @GetMapping("/venus")
    public String getVenus() {
        return "explore/venus.html";
    }

    @GetMapping("/earth")
    public String getEarth() {
        return "explore/earth.html";
    }
    @GetMapping("/mars")
    public String getMars() {
        return "explore/mars.html";
    }
    @GetMapping("/jupiter")
    public String getJupiter() {
        return "explore/jupiter.html";
    }
    @GetMapping("/saturn")
    public String getSaturn() {
        return "explore/saturn.html";
    }
    @GetMapping("/uranus")
    public String getUranus() {
        return "explore/uranus.html";
    }
    @GetMapping("/neptune")
    public String getNeptune() {
        return "explore/neptune.html";
    }
    @GetMapping("/pluto")
    public String getPluto() {
        return "explore/pluto.html";
    }

    @GetMapping("/moon")
    public String getMoon() {
        return "explore/moon.html";
    }
}
