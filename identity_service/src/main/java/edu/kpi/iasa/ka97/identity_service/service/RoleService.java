package edu.kpi.iasa.ka97.identity_service.service;

import edu.kpi.iasa.ka97.identity_service.model.Role;
import edu.kpi.iasa.ka97.identity_service.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role getRoleByName(String name) {
        Optional<Role> role = roleRepository.findRoleByName(name);
        if (role.isPresent()) {
            return role.get();
        }
        throw new IllegalArgumentException("Role not found");
    }

}