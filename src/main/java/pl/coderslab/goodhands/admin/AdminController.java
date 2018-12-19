package pl.coderslab.goodhands.admin;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.goodhands.user.CurrentUser;
import pl.coderslab.goodhands.user.User;

@Controller
public class AdminController {
    @GetMapping("/admin")
    @ResponseBody
    public String iAmAdmin (@AuthenticationPrincipal CurrentUser customUser) {
        User entityUser = customUser.getUser();
        return "this is user id " + entityUser.getId();
    }
}
