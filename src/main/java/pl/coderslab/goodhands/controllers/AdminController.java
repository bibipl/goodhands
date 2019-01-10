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
import pl.coderslab.goodhands.Service.Service;
import pl.coderslab.goodhands.foundation.Foundation;
import pl.coderslab.goodhands.role.Role;
import pl.coderslab.goodhands.Service.RoleService;
import pl.coderslab.goodhands.user.CurrentUser;
import pl.coderslab.goodhands.user.User;
import pl.coderslab.goodhands.Service.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class AdminController {

    @Autowired
    UserService userservice;
    @Autowired
    RoleService roleService;
    @Autowired
    UserService userService;
    @Autowired
    FoundationService foundationService;

    @GetMapping("/admin")
    public String admin (@AuthenticationPrincipal CurrentUser customUser, Model model) {
        User entityUser = customUser.getUser();
        List<User> users = null;
        Role roleAdmin = roleService.findByName("ROLE_ADMIN");
        Role roleUser = roleService.findByName("ROLE_USER");
        model.addAttribute("roleUser", roleUser);
        model.addAttribute("roleAdmin", roleAdmin);
        model.addAttribute("currentUser", entityUser);
        model.addAttribute("users", users);
        return "admin/admin";
    }
    @GetMapping("/admin/roles/{userRole}")
    public String userRoleList (@AuthenticationPrincipal CurrentUser customUser,
                                @PathVariable String userRole, Model model) {
        User entityUser = customUser.getUser();
        List<User> usersAll = userservice.findAll();
        List<User> users = Service.getUserRole(userRole, usersAll);

        // send to view detailed role info is User ? & is Admin ?. Needed to some hrefs and proper view.
        for (User user : users) {
            for (Role role : user.getRoles()) {
                if (role.getName().equals("ROLE_ADMIN")) user.setIsAdmin(true);
                if (role.getName().equals("ROLE_USER")) user.setIsUser(true);
            }
        }
        Role roleAdmin = roleService.findByName("ROLE_ADMIN");
        Role roleUser = roleService.findByName("ROLE_USER");
        model.addAttribute("UA", userRole);
        model.addAttribute("roleUser", roleUser);
        model.addAttribute("roleAdmin", roleAdmin);
        model.addAttribute("currentUser", entityUser);
        model.addAttribute("users", users);
        return "admin/admin";
    }

    @GetMapping("/admin/userStatus/{id}")
    public String admin (@AuthenticationPrincipal CurrentUser customUser, @PathVariable Long id, Model model) {
        User usr = userservice.findById(id);
        if (usr != null) {
            if (usr.getEnabled() == 0) {
                usr.setEnabled(1);
            } else {
                usr.setEnabled(0);
            }
            userservice.saveUser(usr);
        }
        User entityUser = customUser.getUser();
        List<User> usersAll = userservice.findAll();
        List<User> users = Service.getUserRole("ROLE_USER", usersAll);

        // send to view detailed role info is User ? & is Admin ?. Needed to some hrefs and proper view.
        for (User user : users) {
            for (Role role : user.getRoles()) {
                if (role.getName().equals("ROLE_ADMIN")) user.setIsAdmin(true);
                if (role.getName().equals("ROLE_USER")) user.setIsUser(true);
            }
        }
        Role roleAdmin = roleService.findByName("ROLE_ADMIN");
        Role roleUser = roleService.findByName("ROLE_USER");
        model.addAttribute("UA", "ROLE_USER");
        model.addAttribute("roleUser", roleUser);
        model.addAttribute("roleAdmin", roleAdmin);
        model.addAttribute("currentUser", entityUser);
        model.addAttribute("users", users);
        return "admin/admin";
    }

    @GetMapping("/admin/adminStatus/{id}")
    public String adminStatus (@AuthenticationPrincipal CurrentUser customUser, @PathVariable Long id, Model model) {
        User entityUser = customUser.getUser();
        entityUser.setIsAdmin(true);
        entityUser.setIsUser(true);
        User usr = userservice.findById(id);
        List<User> allUsers= userservice.findAll();
        boolean isOnlyOneAdmin = true;
        boolean isAtLeastOneAdmin = false;
        for (User user : allUsers) {
            for (Role role : user.getRoles()) {
                if (role.getName().equals("ROLE_ADMIN")) {
                    if (!isAtLeastOneAdmin) {isAtLeastOneAdmin = true;} // if no admins found yet we have firstone
                    else {isOnlyOneAdmin = false;} // if we have first one yest so we got sanother - more than one exist
                }
            }
        }

        if (usr != null) {
            boolean isAdmin = false;
            Set<Role> allRoles = new HashSet<>();
            for (Role role : usr.getRoles()) {
                if (role.getName().equals("ROLE_ADMIN")) {
                    isAdmin = true;
                } else {
                    allRoles.add(role);
                }
                // if we have only one admin - we cannot remove admin role. We cannot add/remove role for self.
                if ((!isOnlyOneAdmin) && !(entityUser.getId().equals(usr.getId())) && (isAdmin)) {
                    usr.setRoles(allRoles);
                    userservice.saveUser(usr);
                }

            }
            if (!isAdmin) {
                Role role = roleService.findByName("ROLE_ADMIN");
                usr.addRole(role);
                userservice.saveUser(usr);
                return "redirect:/admin/roles/ROLE_ADMIN";
            }
        }
        return "redirect:/admin/roles/ROLE_USER"; // sows users' list
    }

    @GetMapping ("/admin/editUser/{id}")
    public String userEdit (@PathVariable Long id, Model model) {
        User entityUser = userService.findById(id);
        model.addAttribute("user", entityUser);
        return "user/edit";
    }
    @GetMapping ("/admin/deleteUser/{id}")
    public String deleteEdit (@PathVariable Long id, Model model) {
        User entityUser = userService.findById(id);
        model.addAttribute("user", entityUser);
        return "user/delete";
    }
    @GetMapping("/admin/foundationAdd")
    public String foundationAdd (Model model) {
        Foundation foundation = new Foundation();
        model.addAttribute("foundation", foundation);
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
        model.addAttribute("foundation", foundation);
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
