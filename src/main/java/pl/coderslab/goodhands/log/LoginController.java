package pl.coderslab.goodhands.log;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.goodhands.user.CurrentUser;
import pl.coderslab.goodhands.user.User;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login () {

        return "user/login";
    }

    @GetMapping("/logout")
    public String logout (@AuthenticationPrincipal CurrentUser customUser, Model model) {
        if (customUser != null) {
            User entityUser = customUser.getUser();
        model.addAttribute("currentUser", entityUser);
        return "user/logout";
        }
        return "landing";
    }

    @GetMapping("/register")
    public String register() {
        return "user/register";
    }
}
