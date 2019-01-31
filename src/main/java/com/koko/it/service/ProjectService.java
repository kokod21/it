package com.koko.it.service;

import com.koko.it.entity.Project;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProjectService extends BaseService<Project, Long> {
    List<Project> findAllByClassifyId(Long classify_id, Pageable pageable);
}
