package pl.coderslab.goodhands.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    @GetMapping("/admin")
    public String iAmAdmin () {
        return "admin/admin";
    }
}
