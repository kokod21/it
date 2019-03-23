package com.koko.it.service.impl;

import com.koko.it.entity.Permission;
import com.koko.it.repository.PermissionRepository;
import com.koko.it.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class PermissionServiceImpl extends BaseServiceImpl<Permission, Long> implements PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    public void setBaseRepository(PermissionRepository permissionRepository) {
        super.setBaseRepository(permissionRepository);
    }

    @Override
    public List<Map<String, Object>> getPermissionByUserId(Long userId) {
        return permissionRepository.getPermissionByUserId(userId);
    }

    @Override
    public List<Permission> findByPermissionNameNot(String permissionName) {
        return permissionRepository.findByPermissionNameNot(permissionName);
    }

    @Override
    public List<Permission> findByPid(Long pid, Pageable pageable) {
        return permissionRepository.findByPid(pid, pageable);
    }
}
