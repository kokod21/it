package com.koko.it.repository;

import com.koko.it.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<User, Long> {
    User findByUserName(String username);
    User findByUserNameAndPasswordAndIsDel(String username, String password, Integer isDel);
}
