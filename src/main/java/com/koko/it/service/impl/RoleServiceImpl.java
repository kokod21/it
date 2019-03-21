package com.koko.it.service.impl;

import com.koko.it.entity.Role;
import com.koko.it.repository.RoleRepository;
import com.koko.it.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends BaseServiceImpl<Role, Long> implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    public void setBaseRepository(RoleRepository roleRepository) {
        super.setBaseRepository(roleRepository);
    }

}
