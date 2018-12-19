package pl.coderslab.goodhands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.goodhands.role.RoleService;
import pl.coderslab.goodhands.user.CurrentUser;
import pl.coderslab.goodhands.user.User;
import pl.coderslab.goodhands.user.UserService;

@Controller
public class start {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    //(Landing page start for all - to log and other stuff)
    @GetMapping("/")
    public String start(@AuthenticationPrincipal CurrentUser customUser, Model model) {
        if (customUser != null) {
            User entityUser = customUser.getUser();
            model.addAttribute("currentUser", entityUser);
        }
        return "landing";
    }
}

/* //set admin
        Role role = new Role ();
        Role role1 = new Role ();
        role.setName("ROLE_ADMIN");
        role1.setName("ROLE_USER");
        roleService.saveRole (role);
        roleService.saveRole (role1);
        User user = new User();
        user.setUsername("user");
        user.setPassword("123");
        user.setEnabled(1);
        userService.saveUser(user);*/