package com.koko.it.repository;

import com.koko.it.entity.Project;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.List;


@Repository
public interface ProjectRepository extends BaseRepository<Project, Long> {
    List<Project> findAllByClassifyId(Long classify_id, Pageable pageable);
}
