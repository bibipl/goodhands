package pl.coderslab.goodhands.Service;

import pl.coderslab.goodhands.role.Role;

public interface RoleService {

    Role findByName (String name);

    void saveRole (Role role);
}
