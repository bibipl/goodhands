package pl.coderslab.goodhands.role;

public interface RoleService {

    Role findByName (String name);

    void saveRole (Role role);
}
