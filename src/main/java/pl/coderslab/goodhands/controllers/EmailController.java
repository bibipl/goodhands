package pl.coderslab.goodhands.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import pl.coderslab.goodhands.Service.EmailSender;
import pl.coderslab.goodhands.Service.UserService;
import pl.coderslab.goodhands.user.CurrentUser;
import pl.coderslab.goodhands.user.User;


@Controller
public class EmailController {
    private final EmailSender emailSender;
    private final TemplateEngine templateEngine;
    @Autowired
    UserService userService;
    @Autowired
    public EmailController(EmailSender emailSender,
                           TemplateEngine templateEngine){
        this.emailSender = emailSender;
        this.templateEngine = templateEngine;
    }
    @RequestMapping("/regEmail")
    public String send(@AuthenticationPrincipal CurrentUser customUser) {
        User user =  userService.findById(customUser.getUser().getId());
        user.setEnabled(1);
        userService.saveUser(user);
        Context context = new Context();
        context.setVariable("header", "Rejestracja w serwisie Oddam w dobre ręce");
        context.setVariable("title", "Witaj " + user.getUsername() + "!");
        context.setVariable("description", "Dziękujemy za rejestrację w naszym serwisie. Niestety jest to program napisany na zaliczenie więc nie można z niego korzystać tak naprawdę");
        String body = templateEngine.process("templateMail", context);
        emailSender.sendEmail(user.getEmail(), "Oddam w dobre ręce - witamy !", body);
        return "redirect:/user";
    }
}
