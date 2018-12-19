package pl.coderslab.goodhands.admin;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.goodhands.user.CurrentUser;
import pl.coderslab.goodhands.user.User;

@Controller
public class AdminController {
    @GetMapping("/admin")
    public String iAmAdmin (@AuthenticationPrincipal CurrentUser customUser, Model model) {
        User entityUser = customUser.getUser();
        model.addAttribute("currentUser", entityUser);
        return "admin/admin";
    }
}
