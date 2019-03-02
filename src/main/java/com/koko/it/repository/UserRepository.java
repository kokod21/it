package com.koko.it.repository;

import com.koko.it.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<User, Long> {
    User findByUsername(String username);
    User findByUsernameAndPasswordAndIsDel(String username, String password, Integer isDel);
}
