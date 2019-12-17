package space.space.web.view.controllers;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import space.space.services.models.auth.UserServiceModel;
import space.space.services.services.UsersService;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@AllArgsConstructor
@RequestMapping("/user")
public class UsersController {
    private final UsersService usersService;
    private final ModelMapper mapper;

    @GetMapping("/profile")
    public String getProfile() {
        return "/user/profile";
    }

    @PostMapping("/profile")
    public String postProfile() {
        return "/user/profile";
    }

    @GetMapping("/admin_tab")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView getAdminTab(ModelAndView modelAndView, HttpSession session) {
        List<UserServiceModel> admins = usersService.findAllUsers()
                .stream()
                .map(user -> mapper.map(user, UserServiceModel.class))
                .filter(user -> user.getAuthorities().size()==2)
                .collect(Collectors.toList());
                modelAndView.addObject("admins", admins);

        List<UserServiceModel> users = usersService.findAllUsers()
                .stream()
                .map(user -> mapper.map(user, UserServiceModel.class))
                .filter(user -> user.getAuthorities().size()==1)
                .collect(Collectors.toList());
        modelAndView.addObject("users", users);
        return modelAndView;
    }

    @GetMapping("/admin_tab/{promote}")
    public String promote(@PathVariable long promote)  {
        usersService.promoteToAdmin(promote);
        return "redirect:/user/admin_tab";
    }
    @GetMapping("/admin_tabD/{demote}")
    public String demote(@PathVariable long demote)  {
        usersService.demoteToUser(demote);
        return "redirect:/user/admin_tab";
    }
}
