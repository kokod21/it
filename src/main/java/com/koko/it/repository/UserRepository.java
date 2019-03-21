package com.koko.it.repository;

import com.koko.it.entity.User;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserRepository extends BaseRepository<User, Long> {
    User findByUserName(String username);
    User findByUserNameAndPasswordAndIsDel(String username, String password, Integer isDel);

    @Query(value = "select t1.*, date_format(t1.create_time, '%Y-%m-%d %H:%i:%s' ) as create_t,date_format(t1.update_time, '%Y-%m-%d %H:%i:%s' ) as update_t, " +
            "t2.user_name as create_user_name, t3.user_name as update_user_name , t5.role_name from user t1 " +
            "left join user t2 on t2.id = t1.create_user_id " +
            "left join user t3 on t3.id = t1.update_user_id " +
            "left join user_role t4 on t4.user_id=t1.id " +
            "left join role t5 on t5.id=t4.role_id", nativeQuery = true)
    List<Map<String, Object>> findAllUser(PageRequest pageRequest);

    @Query(value = "select t1.*, t2.role_id from user t1\n" +
            "left join user_role t2 on t1.id=t2.user_id\n" +
            "where t1.id = :user_id", nativeQuery = true)
    Map<String, Object> findUserAndRole(@Param("user_id") Long user_id);
}
