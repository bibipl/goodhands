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
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import pl.coderslab.goodhands.Service.EmailSender;
import pl.coderslab.goodhands.Service.VerificationTokenService;
import pl.coderslab.goodhands.role.Role;
import pl.coderslab.goodhands.Service.RoleService;
import pl.coderslab.goodhands.user.CurrentUser;
import pl.coderslab.goodhands.user.User;
import pl.coderslab.goodhands.Service.UserService;
import pl.coderslab.goodhands.verificationToken.VerificationToken;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Controller
public class LoginController {

    @Autowired
    TemplateEngine templateEngine;
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    EmailSender emailSender;
    @Autowired
    VerificationTokenService verificationTokenService;

    @GetMapping("/login")
    public String login () {
        return "user/login";
    }


    @GetMapping("/logout")
    public String logout (@AuthenticationPrincipal CurrentUser customUser, Model model) {
        if (customUser != null) {
            User entityUser = customUser.getUser();
        model.addAttribute("currentUser", entityUser);
        return "/user/logout";
        }
        return "/landing";
    }

    @GetMapping("/register")
    public String register(Model model) {
            User user = new User();
            model.addAttribute("user", user);
        return "/user/register";
    }

    @PostMapping("/register")
    public String registerAction (@ModelAttribute @Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "/user/register";
        }
        else if (!user.getPassword().equals(user.getPasswordCheck())) {
            return"/user/register";
        }

        User userCheckEmail = userService.findByEmail(user.getEmail());
        User userCheckUsername = userService.findByUserName(user.getUsername());

        if (userCheckEmail == null && userCheckUsername == null) {
            Role role=roleService.findByName("ROLE_USER");
            Set<Role> allRoles = new HashSet<>();
            allRoles.add(role);
            user.setRoles(allRoles);
            user.setEnabled(0);
            String token = UUID.randomUUID().toString();
            userService.saveUser(user); // we have to save to get id.
            VerificationToken verToken = new VerificationToken();
            verToken.setUser(user);
            verToken.setToken(token);
            verificationTokenService.save(verToken);
            Context context = new Context();
            context.setVariable("header", "Rejestracja w serwisie Oddam w dobre ręce");
            context.setVariable("title", "Witaj " + user.getUsername() + "!");
            context.setVariable("description", "Dziękujemy za rejestrację w naszym serwisie. Dokoncz swoj proces rejestracji");
            context.setVariable("link","http://localhost:8080/confirm/" + token + "/" + user.getId());
            String body = templateEngine.process("templateMail", context);
            emailSender.sendEmail(user.getEmail(), "Oddam w dobre ręce - witamy !", body);
            return "user/login";
        }
        return "/user/register";
    }
    @GetMapping ("/confirm/{token}/{id}")
    public String tokenConfirmation (@PathVariable("token")String token, @PathVariable ("id") Long id) {
        // So far do nothing until token entity has been created
        // The plan ids to take token and user id. Find token by userid and compare them.
        User user = userService.findById(id);
        if (user != null) {
            List<VerificationToken> verTokens = verificationTokenService.findAllByUserId(id);
            if (verTokens != null) {
                for (VerificationToken verToken : verTokens) {
                    if (verToken.getToken().equals(token)) {
                        user.setEnabled(1);
                        userService.saveUser(user);
                    }
                    verificationTokenService.delete(verToken);
                }
            }
        }
        return "user/login";
    }
}
