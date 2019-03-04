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
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public User findByUserNameAndPasswordAndIsDel(String username, String password, Integer isDel) {
        return userRepository.findByUserNameAndPasswordAndIsDel(username, password, isDel);
    }
}
