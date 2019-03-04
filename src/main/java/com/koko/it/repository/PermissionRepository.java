package com.koko.it.repository;

import com.koko.it.entity.Permission;
import com.koko.it.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public interface PermissionRepository extends BaseRepository<Permission, Long> {

    @Query(value = "SELECT p.id, p.name,p.pid, p.sort, p.classsify, p.code, p.icon, p.url " +
            " FROM permission p " +
            " LEFT JOIN role_permission rp ON rp.permission_id=p.id " +
            " LEFT JOIN role r ON r.id=rp.role_id " +
            " LEFT JOIN user_role ur ON ur.role_id=r.id " +
            " WHERE ur.user_id=:userId AND p.name <> '' GROUP BY p.id ORDER BY p.zindex", nativeQuery = true)
    List<Map<String, Object>> getPermissionByUserId(@Param("userId") Integer userId);

    List<Permission> findByNameNot(String name);
}
