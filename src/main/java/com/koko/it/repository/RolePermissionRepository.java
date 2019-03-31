package com.koko.it.repository;

import com.koko.it.entity.RolePermission;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface RolePermissionRepository extends BaseRepository<RolePermission, Long> {

    List<RolePermission> findByRoleId(Long roleId);

    @Transactional
    @Modifying
    void deleteByRoleId(Long roleId);
}
