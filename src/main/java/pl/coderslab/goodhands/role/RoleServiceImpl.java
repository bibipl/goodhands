package pl.coderslab.goodhands.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
