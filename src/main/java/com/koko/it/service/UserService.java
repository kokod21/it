package com.koko.it.service;

import com.koko.it.entity.User;

public interface UserService extends BaseService<User, Long> {
    User findByUserName(String username);
    User findByUserNameAndPasswordAndIsDel(String username, String password, Integer isDel);
}
