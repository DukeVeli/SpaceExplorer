package space.space.web.view.controllers;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import space.space.services.models.creditAccount.CreditAccountServiceModel;
import space.space.services.services.CreditAccountService;
import space.space.web.base.BaseController;
import space.space.web.view.models.CreditAddModel;

import javax.servlet.http.HttpSession;


@Controller
@AllArgsConstructor
@RequestMapping("/account")
public class CreditAccountController extends BaseController {
    private final CreditAccountService creditAccountService;
    private final ModelMapper mapper;


    @GetMapping("/manage")
    public String getProfile() {
        return "/account/manage";
    }

    @PostMapping("/add")
    public String addCredits(@ModelAttribute CreditAddModel model,HttpSession session) {
        CreditAccountServiceModel mod = mapper.map(model,CreditAccountServiceModel.class);
        session.setAttribute("username",mod.getUserName());

        String username = mod.getUserName();
        creditAccountService.add(mod,username);

        return "redirect:/account/manage";
    }

}
