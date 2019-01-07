package pl.coderslab.goodhands.log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.goodhands.role.Role;
import pl.coderslab.goodhands.role.RoleService;
import pl.coderslab.goodhands.user.CurrentUser;
import pl.coderslab.goodhands.user.User;
import pl.coderslab.goodhands.user.UserService;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

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
    public String register(Model model) {
            User user = new User();
            model.addAttribute("user", user);
        return "user/register";
    }

    @PostMapping("/register")
    public String registerAction (@ModelAttribute @Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "user/register";
        }
        else if (!user.getPassword().equals(user.getPasswordCheck())) {
            return"user/register";
        }

        User userCheckEmail = userService.findByEmail(user.getEmail());
        User userCheckUsername = userService.findByUserName(user.getUsername());

        if (userCheckEmail == null && userCheckUsername == null) {
            Role role=roleService.findByName("ROLE_USER");
            Set<Role> allRoles = new HashSet<>();
            allRoles.add(role);
            user.setRoles(allRoles);
            userService.saveUser(user);
            return "redirect:/login";
        }
        return "user/register";
    }
}
