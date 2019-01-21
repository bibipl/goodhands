package pl.coderslab.goodhands.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.goodhands.Service.FoundationService;
import pl.coderslab.goodhands.Service.GiftService;
import pl.coderslab.goodhands.Service.UserService;
import pl.coderslab.goodhands.foundation.Foundation;
import pl.coderslab.goodhands.gift.Cl;
import pl.coderslab.goodhands.gift.Gift;
import pl.coderslab.goodhands.user.CurrentUser;
import pl.coderslab.goodhands.user.User;

import java.util.ArrayList;
import java.util.List;

@Controller
public class GiftController {

    @Autowired
    UserService userService;
    @Autowired
    GiftService giftService;
    @Autowired
    FoundationService foundationService;

    @GetMapping("/user/gifts")
    public String showGifts(@AuthenticationPrincipal CurrentUser customUser, Model model) {
        User user = customUser.getUser();
        List<Gift> myGifts = giftService.findAllByUserId(user.getId());
        model.addAttribute("user", user);
        model.addAttribute("gifts", myGifts);
        return "/gift/userGifts";
    }

    @GetMapping("user/addGift")
    public String addGift(@AuthenticationPrincipal CurrentUser customUser, Model model) {
        Gift gift = new Gift();
        User user = customUser.getUser();
        List<String> items = Cl.item();
        model.addAttribute("user", user);
        model.addAttribute("gift", gift);
        model.addAttribute("items", items);
        return "/gift/addGift1";
    }
    @PostMapping("user/addGift")
    //we want "what"
    public String addGiftAction (@AuthenticationPrincipal CurrentUser customUser, @ModelAttribute Gift gift, Model model){
        User user = customUser.getUser();
        List<String> items = Cl.item();
        List<String> subContent1 = new ArrayList<>();
        List<String> subContent2 = new ArrayList<>();
        int selected = 0;
        String content = gift.getContent();
        if (content.equals(items.get(0))) {
            //"Rzeczy do oddania" - additional options
            subContent1 = Cl.clothesForWhom();
            subContent2 = Cl.clothesForWhat();
            selected = 1;
        }
        else if (content.equals(items.get(1))) {
            // "do wyrzucenia" - no additional options
            subContent1 = null;
            subContent2 = null;
            selected = 2;
        }
        else if (content.equals(items.get(2))) {
            subContent1 = Cl.childrenGender();
            subContent2 = Cl.childrenAge();
            selected = 3;
        }
        else if (content.equals(items.get(3))) {
            subContent1 = Cl.booksForWhom();
            subContent2 = null;
            selected = 4;
        }
        else if (content.equals(items.get(4))) {
            subContent1 = null;
            subContent2 = null;
            selected =5;
        }
        else return ("redirect:/");
        gift.setCompleted(1);
        model.addAttribute("sub1", subContent1);
        model.addAttribute("sub2", subContent2);
        model.addAttribute("user", user);
        model.addAttribute("gift", gift);
        model.addAttribute("selected", selected);
        return "/gift/addGift12";
    }

    @PostMapping("/user/addGift2")
    //here we have "what" and we want "how many bags"
    public String addGift2(@AuthenticationPrincipal CurrentUser customUser, @ModelAttribute Gift gift, Model model) {
        User user = customUser.getUser();
        List<String> bags = Cl.numberOfBags();
        model.addAttribute("user", user);
        model.addAttribute("gift", gift);
        model.addAttribute("bags",bags);
        return"/gift/addGift2";
    }

    @PostMapping("/user/addGift3")
    //here we have "what" and "how many", we want to know "foundation"
    public String addGift3(@AuthenticationPrincipal CurrentUser customUser, @ModelAttribute Gift gift, Model model) {
        User user = customUser.getUser();
        List<String> regions = Cl.region();
        List<Foundation> foundations = foundationService.findAll();
        model.addAttribute("foundations", foundations);
        model.addAttribute("gift", gift);
        model.addAttribute("regions", regions); // so fat not needed - to filter later by regions
        return"/gift/addGift3";
    }

    @PostMapping("/user/addGift31")
    //here we have "what" and "how many", we want to know "foundation"
    public String addGift31(@AuthenticationPrincipal CurrentUser customUser, @ModelAttribute Gift gift, Model model) {
        User user = customUser.getUser();
        Foundation foundation = foundationService.findById(gift.getFoundation().getId());
        gift.setFoundation(foundation);
        gift.setUser(user);
        gift.setCompleted(3);
        model.addAttribute("gift", gift);
        return"/gift/addGift4";
    }

    @PostMapping("/user/addGift4")
    //here we have "what" and "how many", we want to know "foundation"
    public String addGift4(@AuthenticationPrincipal CurrentUser customUser, @ModelAttribute Gift gift, Model model) {
        User user = customUser.getUser();
        Foundation foundation = foundationService.findById(gift.getFoundation().getId());
        gift.setFoundation(foundation);
        gift.setUser(user);
        gift.setCompleted(4);
        model.addAttribute("gift", gift);
        return"/gift/addGiftSummary";
    }

    @PostMapping("/user/addGiftS")
    //here we have "what" and "how many", we want to know "foundation"
    public String addGiftS(@AuthenticationPrincipal CurrentUser customUser, @ModelAttribute Gift gift, Model model) {
        User user = customUser.getUser();
        gift.setCompleted(5);
        giftService.save(gift);
        return"redirect:/user/";
    }

}