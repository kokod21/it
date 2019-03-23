package com.koko.it.service;

import com.koko.it.entity.Permission;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface PermissionService extends BaseService<Permission, Long> {
    List<Map<String, Object>> getPermissionByUserId(Long userId);

    List<Permission> findByPermissionNameNot(String permissionName);
    List<Permission> findByPid(Long pid, Pageable pageable);
}
