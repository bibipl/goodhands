package pl.coderslab.goodhands.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.goodhands.repository.RoleRepository;
import pl.coderslab.goodhands.role.Role;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;
    @Override
    public Role findByName(String name) {
        return roleRepository.findByName (name);
    }

    @Override
    public void saveRole(Role role) {
        roleRepository.save(role);
    }
}
