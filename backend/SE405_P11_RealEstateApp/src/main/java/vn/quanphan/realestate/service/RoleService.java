package vn.quanphan.realestate.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import vn.quanphan.realestate.domain.Role;
import vn.quanphan.realestate.repository.RoleRepository;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public Role findRoleByName(String name) {
        return roleRepository.findByName(name);
    }
}
