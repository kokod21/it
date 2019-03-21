package com.koko.it.service.impl;

import com.koko.it.entity.Permission;
import com.koko.it.entity.User;
import com.koko.it.repository.PermissionRepository;
import com.koko.it.repository.UserRepository;
import com.koko.it.service.PermissionService;
import com.koko.it.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Permission> findByNameNot(String name) {
        return permissionRepository.findByNameNot(name);
    }
}
