package pl.coderslab.goodhands.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.goodhands.Service.FoundationService;
import pl.coderslab.goodhands.foundation.Foundation;
import pl.coderslab.goodhands.role.Role;
import pl.coderslab.goodhands.user.CurrentUser;
import pl.coderslab.goodhands.user.User;

import java.util.List;

@Controller
public class FoundationController {
    @Autowired
    FoundationService foundationService;

    @GetMapping ("/foundation")
    public String foundationList (@AuthenticationPrincipal CurrentUser customUser, Model model) {
        User entityUser = null;
        boolean isAdmin = false;
        if (customUser != null) {
            entityUser = customUser.getUser();
            for (Role role : entityUser.getRoles()) {
                if (role.getName().equals("ROLE_ADMIN")) isAdmin = true;
            }
        }
        List<Foundation> foundations = foundationService.findAll();
        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("user", entityUser);
        model.addAttribute("foundations", foundations);
        return "/foundation/foundation";
    }
}
