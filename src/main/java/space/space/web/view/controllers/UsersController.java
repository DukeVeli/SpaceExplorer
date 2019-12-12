package space.space.web.view.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UsersController {
    @GetMapping("/profile")
    public String getProfile() {
        return "/user/profile.html";
    }

    @PostMapping("/profile")
    public String postProfile() {
        return "/user/profile.html";
    }
}
