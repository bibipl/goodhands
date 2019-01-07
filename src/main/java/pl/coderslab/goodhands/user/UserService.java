package pl.coderslab.goodhands.user;

import java.util.List;

public interface UserService {

    User findByUserName(String name);
    User findByEmail(String email);
    User findById (Long id);
    List<User> findAll ();
    void saveUser(User user);
    void  delete (User user);

}
