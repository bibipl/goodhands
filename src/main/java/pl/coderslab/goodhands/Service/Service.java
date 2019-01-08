package pl.coderslab.goodhands.Service;

import pl.coderslab.goodhands.role.Role;
import pl.coderslab.goodhands.user.User;

import java.util.ArrayList;
import java.util.List;

public class Service {

    // ### The method returns list of users thet have specified Role's name)
    public static List<User> getUserRole (String role, List<User> usersAll) {
        List<User> users = new ArrayList<>();
        for (User user : usersAll) {
            if (user.getRoles() != null) {
                boolean hasRole = false;
                for (Role userRole : user.getRoles()) {
                    if (userRole.getName().equals(role)) hasRole = true;
                }
                if (hasRole) users.add(user);
            }
        }
        return users;
    }
}
