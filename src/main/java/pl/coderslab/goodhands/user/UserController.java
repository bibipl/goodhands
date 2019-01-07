package pl.coderslab.goodhands.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/user")
    public String iAmUser (@AuthenticationPrincipal CurrentUser customUser, Model model) {
        User entityUser = userService.findById(customUser.getUser().getId());
        model.addAttribute("currentUser", entityUser);
        return "user/user";
    }

    @GetMapping("/user/edit")
    public String editUser (@AuthenticationPrincipal CurrentUser customUser, Model model) {
        User entityUser = userService.findById(customUser.getUser().getId());
        model.addAttribute("user", entityUser);
        return "user/edit";
    }
    @PostMapping("user/edit")
    public String editUserAction (@ModelAttribute User user, BindingResult result) {
        boolean isChange = false;
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
        return "redirect:/user";
    }
    @GetMapping ("/user/delete")
    public String delete (@AuthenticationPrincipal CurrentUser customUser, Model model) {
        User user = userService.findById(customUser.getUser().getId());
        model.addAttribute(user);
        return "user/delete";
    }
    @GetMapping("/user/deleteAction")
    public String deleteAction (@AuthenticationPrincipal CurrentUser customUser) {
        User user = userService.findById(customUser.getUser().getId());
        userService.delete(user);
        return "landing";
    }
}
