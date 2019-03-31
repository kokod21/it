package com.koko.it.repository;

import com.koko.it.entity.UserRole;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends BaseRepository<UserRole, Long> {
    UserRole findByUserId(Long userId);
    void deleteByUserId(Long userId);
}
