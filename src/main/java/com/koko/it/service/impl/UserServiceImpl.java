package com.koko.it.service.impl;

import com.koko.it.entity.User;
import com.koko.it.entity.UserRole;
import com.koko.it.repository.UserRepository;
import com.koko.it.service.UserRoleService;
import com.koko.it.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, Long> implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserRoleService userRoleService;

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

    @Override
    public List<Map<String, Object>> findAllUser(PageRequest pageRequest) {
        return userRepository.findAllUser(pageRequest);
    }

    @Transactional
    public void saveUser(User user, Long roleId) {
        if (user.getId() == null) {
            User newUser = userRepository.save(user);
            UserRole userRole = new UserRole();
            userRole.setRoleId(roleId);
            userRole.setUserId(newUser.getId());
            userRoleService.save(userRole);
        } else {
            userRepository.save(user);
            UserRole userRole = userRoleService.findByUserId(user.getId());
            if (userRole == null) {
                userRole = new UserRole();
                userRole.setUserId(user.getId());
            }
            userRole.setRoleId(roleId);
            userRoleService.save(userRole);
        }
    }

    @Override
    public Map<String, Object> findUserAndRole(Long user_id) {
        return userRepository.findUserAndRole(user_id);
    }
}
