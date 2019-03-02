package com.koko.it.service.impl;

import com.koko.it.entity.User;
import com.koko.it.repository.UserRepository;
import com.koko.it.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl extends BaseServiceImpl<User, Long> implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public void setBaseRepository(UserRepository userRepository) {
        super.setBaseRepository(userRepository);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByUsernameAndPasswordAndIsDel(String username, String password, Integer isDel) {
        return userRepository.findByUsernameAndPasswordAndIsDel(username, password, isDel);
    }
}
