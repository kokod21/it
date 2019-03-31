package com.koko.it.service.impl;

import com.koko.it.entity.RolePermission;
import com.koko.it.repository.RolePermissionRepository;
import com.koko.it.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolePermissionServiceImpl extends BaseServiceImpl<RolePermission, Long> implements RolePermissionService {

    @Autowired
    private RolePermissionRepository rolePermissionRepository;

    @Autowired
    public void setBaseRepository(RolePermissionRepository rolePermissionRepository) {
        super.setBaseRepository(rolePermissionRepository);
    }

    @Override
    public List<RolePermission> findByRoleId(Long roleId) {
        return rolePermissionRepository.findByRoleId(roleId);
    }

    @Override
    public void deleteByRoleId(Long roleId) {
        rolePermissionRepository.deleteByRoleId(roleId);
    }
}
