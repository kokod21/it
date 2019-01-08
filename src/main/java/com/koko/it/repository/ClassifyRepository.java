package com.koko.it.repository;

import com.koko.it.entity.Classify;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public interface ClassifyRepository extends BaseRepository<Classify, Long> {

    @Query(value = "select t1.*, t2.parent_name from classify t1 " +
            "left join classify_parent t2 on t1.parent_id = t2.id ", nativeQuery = true)
    List<Map<String, Object>> findAllClassify();
}
