package pl.coderslab.goodhands.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.goodhands.Service.FoundationService;
import pl.coderslab.goodhands.foundation.Foundation;
import pl.coderslab.goodhands.gift.Cl;
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
    @GetMapping("/admin/foundationAdd")
    public String foundationAdd (Model model) {
        Foundation foundation = new Foundation();
        List<String> regions = Cl.region();
        model.addAttribute("foundation", foundation);
        model.addAttribute("regions", regions);
        return "/foundation/foundationAdd";
    }
    @PostMapping("/admin/foundationAdd")
    public String foundationAdd (@ModelAttribute Foundation foundation, BindingResult result) {
        if (result.hasErrors()) {
            return ("redirect:/foundation/foundationAdd");
        }
        Foundation foundationCheckName = null;
        Foundation foundationCheckEmail = null;
        // if id == null - add new foundation. if id !=null - edit foundation - name and email already exist.
        if (foundation.getId() == null) {
            foundationCheckName = foundationService.findByName(foundation.getName());
            foundationCheckEmail = foundationService.findByName(foundation.getEmail());
        }
        if (foundationCheckEmail != null || foundationCheckName != null) {
            return ("redirect:/foundation/foundationAdd");
        }
        foundationService.save(foundation);
        return ("redirect:/foundation");
    }

    @GetMapping ("/admin/editFoundation/{id}")
    public String foundationEdit (@PathVariable Long id, Model model) {
        Foundation foundation = foundationService.findById(id);
        List<String> regions = Cl.region();
        model.addAttribute("foundation", foundation);
        model.addAttribute("regions", regions);
        return "/foundation/foundationAdd";
    }
    @GetMapping ("/admin/deleteFoundation/{id}")
    public String deleteFoundation (@PathVariable Long id, Model model) {
        Foundation foundation = foundationService.findById(id);
        model.addAttribute("foundation", foundation);
        return "/foundation/foundationDelete";
    }
    @GetMapping ("/admin/deleteFoundationAction/{id}")
    public String deleteFoundationAction (@PathVariable Long id, Model model) {
        Foundation foundation = foundationService.findById(id);
        foundationService.delete(foundation);
        return "redirect:/foundation";
    }
}
