package com.koko.it.service.impl;

import com.koko.it.entity.UserRole;
import com.koko.it.repository.UserRoleRepository;
import com.koko.it.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl extends BaseServiceImpl<UserRole, Long> implements UserRoleService {

    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    public void setBaseRepository(UserRoleRepository userRoleRepository) {
        super.setBaseRepository(userRoleRepository);
    }


    @Override
    public UserRole findByUserId(Long userId) {
        return userRoleRepository.findByUserId(userId);
    }

}
