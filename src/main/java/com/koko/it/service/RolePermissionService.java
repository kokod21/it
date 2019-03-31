package com.koko.it.service;

import com.koko.it.entity.RolePermission;

import java.util.List;

public interface RolePermissionService extends BaseService<RolePermission, Long> {

    List<RolePermission> findByRoleId(Long roleId);
    void deleteByRoleId(Long roleId);
}
