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
import pl.coderslab.goodhands.role.Role;
import pl.coderslab.goodhands.user.CurrentUser;
import pl.coderslab.goodhands.user.User;
import pl.coderslab.goodhands.Service.UserService;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/user")
    public String iAmUser (@AuthenticationPrincipal CurrentUser customUser, Model model) {
        User entityUser = userService.findById(customUser.getUser().getId());
        if (entityUser.getEnabled() == 0) {
            return "redirect:/regEmail";
        }
        model.addAttribute("currentUser", entityUser);
        return "user/user";
    }

    @GetMapping("/user/edit/{id}")
    public String editUser (@AuthenticationPrincipal CurrentUser customUser, Model model) {
        User entityUser = userService.findById(customUser.getUser().getId());
        model.addAttribute("user", entityUser);
        return "user/edit";
    }
    @PostMapping("user/edit")
    public String editUserAction (@AuthenticationPrincipal CurrentUser customUser,@ModelAttribute User user, BindingResult result) {
        boolean isChange = false;
        // to know if to return to admin panel or user panel.
        boolean adminAction=false;
        User entityAdmin = customUser.getUser();
        for (Role role : entityAdmin.getRoles()) {
            if (role.getName().equals("ROLE_ADMIN") && entityAdmin.getId()!= user.getId()) {
              adminAction = true;
            }
        }
        if (!user.getPassword().equals(user.getPasswordCheck())) {
            return"user/edit";
        }

        User entityUser = userService.findById(user.getId()); // we will compare data from form with data from Db.
        // If changes in username has been done then :
        if (!entityUser.getUsername().equals(user.getUsername())) {
            // now we check if such (changed) username exists :
            User userCheckUsername = userService.findByUserName(user.getUsername());
            if (userCheckUsername == null) {
                entityUser.setUsername(user.getUsername());
                isChange = true;
            }
        }
        // If changes in email has been done then :
        if (!entityUser.getEmail().equals(user.getEmail())) {
            // now we check if such (changed) email exists :
            User userCheckEmail = userService.findByEmail(user.getEmail());
            if (userCheckEmail == null) {
                entityUser.setEmail(user.getEmail());
                isChange = true;
            }
        }
        if (user.getPassword().equals(user.getPasswordCheck()) && !user.getPassword().equals("")) {
            entityUser.setPassword(user.getPassword());
            entityUser.setPasswordCheck(user.getPasswordCheck());
            isChange = true;
        }
        if (isChange) {
            userService.saveUser(entityUser);
        }
        if (adminAction) return "redirect:/admin/roles/ROLE_USER";
        return "redirect:/user";
    }
    @GetMapping ("/user/delete/{id}")
    public String delete (@PathVariable Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute(user);
        return "user/delete";
    }
    @GetMapping("/user/deleteAction/{id}")
    public String deleteAction (@AuthenticationPrincipal CurrentUser customUser, @PathVariable Long id) {
        User user = userService.findById(id);
        boolean theSame = true; // we check we this is delfdestruction or admin action - to return to proper place;
        User entityUser = userService.findById(customUser.getUser().getId());
        if (user.getId() != entityUser.getId()) theSame = false;
        if (theSame) userService.delete(user);
        // here someone else tries to delete. We have to know it is admin and we cannot give /admin protection as
        // user also can removehimself.
        else  {
            for (Role role : customUser.getUser().getRoles()) {
                if (role.equals("ROLE_ADMIN")) {
                    userService.delete(user);
                    return "redirect:/admin/roles/ROLE_USER";
                }
            }
        }
        return "landing";
    }
}
