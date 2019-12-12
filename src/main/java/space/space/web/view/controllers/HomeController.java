package space.space.web.view.controllers;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import space.space.services.services.AuthenticatedUserService;
import space.space.services.services.CreditAccountService;
import space.space.services.services.PlanetService;
import space.space.web.base.BaseController;

import javax.servlet.http.HttpSession;

@Controller
@AllArgsConstructor
public class HomeController  extends BaseController {
    private final PlanetService planetService;
    private final ModelMapper modelMapper;
    private final AuthenticatedUserService authenticatedUserService;
    private final CreditAccountService accountService;

    @GetMapping("/")
    public String getIndex(HttpSession session) {
        return "home/index.html";
    }

}
