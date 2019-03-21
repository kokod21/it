package com.koko.it.service;

import com.koko.it.entity.User;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Map;

public interface UserService extends BaseService<User, Long> {
    User findByUserName(String username);
    User findByUserNameAndPasswordAndIsDel(String username, String password, Integer isDel);
    List<Map<String, Object>> findAllUser(PageRequest pageRequest);

    void saveUser(User user, Long roleId);
    Map<String, Object> findUserAndRole(Long user_id);
}
