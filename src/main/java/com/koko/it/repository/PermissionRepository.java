package com.koko.it.repository;

import com.koko.it.entity.Permission;
import com.koko.it.entity.User;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public interface PermissionRepository extends BaseRepository<Permission, Long> {

    @Query(value = "SELECT p.id, p.permission_name,p.pid, p.sort, p.classify, p.code, p.icon, p.url " +
            " FROM permission p " +
            " LEFT JOIN role_permission rp ON rp.permission_id=p.id " +
            " LEFT JOIN role r ON r.id=rp.role_id " +
            " LEFT JOIN user_role ur ON ur.role_id=r.id " +
            " WHERE ur.user_id=:userId AND p.permission_name <> '' GROUP BY p.id ORDER BY p.sort", nativeQuery = true)
    List<Map<String, Object>> getPermissionByUserId(@Param("userId") Long userId);

    List<Permission> findByPermissionNameNot(String permissionName);
    List<Permission> findByPid(Long pid, Pageable pageable);
}
