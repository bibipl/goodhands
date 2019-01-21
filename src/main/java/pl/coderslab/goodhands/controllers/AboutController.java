package pl.coderslab.goodhands.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutController {

    @GetMapping ("/aboutUs")
    public String aboutUs () {
      return ("/about/about");
    }

    @GetMapping ("/whatsOn")
    public String whatsOn () {
        return ("/about/whats");
    }

    @GetMapping ("/contact")
    public String contact () {
        return ("/about/contact");
    }
}
