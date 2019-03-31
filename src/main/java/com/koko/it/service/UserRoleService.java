package com.koko.it.service;

import com.koko.it.entity.UserRole;

public interface UserRoleService extends BaseService<UserRole, Long> {
    UserRole findByUserId(Long userId);
    void deleteByUserId(Long userId);
}
