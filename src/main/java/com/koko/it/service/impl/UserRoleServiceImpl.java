package com.koko.it.service.impl;

import com.koko.it.entity.User;
import com.koko.it.entity.UserRole;
import com.koko.it.repository.UserRoleRepository;
import com.koko.it.service.UserRoleService;
import com.koko.it.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserRoleServiceImpl extends BaseServiceImpl<UserRole, Long> implements UserRoleService {

    @Autowired
    UserRoleRepository userRoleRepository;
    @Autowired
    UserService userService;

    @Autowired
    public void setBaseRepository(UserRoleRepository userRoleRepository) {
        super.setBaseRepository(userRoleRepository);
    }

    @Override
    public UserRole findByUserId(Long userId) {
        return userRoleRepository.findByUserId(userId);
    }

    @Transactional
    @Override
    public void deleteByUserId(Long userId) {
        User user = userService.findById(userId).get();
        userService.delete(user);
        userRoleRepository.deleteByUserId(userId);
    }
}
