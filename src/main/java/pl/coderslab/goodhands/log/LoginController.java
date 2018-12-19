package pl.coderslab.goodhands.log;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login () {

        return "user/login";
    }

    @GetMapping("/logout")
    public String logoutForm () {

        return "user/logout";
    }
}
