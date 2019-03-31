package com.koko.it.service;

import com.koko.it.entity.Role;
import com.koko.it.entity.User;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Map;

public interface RoleService extends BaseService<Role, Long> {

    void saveRole(Role role, String[] permissionIds);
    void deleteRole(Long id);

}
