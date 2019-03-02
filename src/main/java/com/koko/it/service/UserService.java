package com.koko.it.service;

import com.koko.it.entity.User;

public interface UserService extends BaseService<User, Long> {
    User findByUsername(String username);
    User findByUsernameAndPasswordAndIsDel(String username, String password, Integer isDel);
}
