package com.koko.it.service.impl;

import com.koko.it.entity.Role;
import com.koko.it.entity.RolePermission;
import com.koko.it.repository.RoleRepository;
import com.koko.it.service.RolePermissionService;
import com.koko.it.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleServiceImpl extends BaseServiceImpl<Role, Long> implements RoleService {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RolePermissionService rolePermissionService;

    @Autowired
    public void setBaseRepository(RoleRepository roleRepository) {
        super.setBaseRepository(roleRepository);
    }

    @Transactional
    @Override
    public void saveRole(Role role, String[] permissionIds) {
        if (role.getId() != null) {
            rolePermissionService.deleteByRoleId(role.getId());
        }
        Role newRole = roleRepository.save(role);
        for (String permissionId : permissionIds) {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRoleId(newRole.getId());
            rolePermission.setPermissionId(Long.parseLong(permissionId));
            rolePermissionService.save(rolePermission);
        }
    }

    @Transactional
    @Override
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
        rolePermissionService.deleteByRoleId(id);
    }
}
