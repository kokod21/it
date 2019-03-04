package com.koko.it.service;

import com.koko.it.entity.Permission;

import java.util.List;
import java.util.Map;

public interface PermissionService extends BaseService<Permission, Long> {
    List<Map<String, Object>> getPermissionByUserId(Integer userId);

    List<Permission> findByNameNot(String name);
}
