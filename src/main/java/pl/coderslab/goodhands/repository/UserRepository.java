package pl.coderslab.goodhands.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.goodhands.user.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    User findByEmail(String email);
    User findById (Long id);
}
