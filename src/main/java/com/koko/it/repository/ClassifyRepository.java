package com.koko.it.repository;

import com.koko.it.entity.Classify;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public interface ClassifyRepository extends BaseRepository<Classify, Long> {

    List<Classify> findByParentIdNot(Long parentId);

    List<Classify> findByParentId(Long parentId);

    @Modifying
    @Query(value = "delete from classify where id = :id or parent_id = :parentId", nativeQuery = true)
    void deleteClassifyById(@Param("id") Long id, @Param("parentId") Long parentId);
}
