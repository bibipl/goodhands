package pl.coderslab.goodhands.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.goodhands.role.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String name);
}


