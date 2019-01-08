package pl.coderslab.goodhands.Service;

import pl.coderslab.goodhands.user.User;

import java.util.List;

public interface UserService {

    User findByUserName(String name);
    User findByEmail(String email);
    User findById (Long id);
    List<User> findAll ();
    void saveUser(User user);
    void  delete (User user);

}
