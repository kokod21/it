package com.koko.it.service.impl;

import com.koko.it.entity.Project;
import com.koko.it.repository.ProjectRepository;
import com.koko.it.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.List;


@Service
public class GithubServiceImpl extends BaseServiceImpl<Project, Long> implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    public void setBaseRepository(ProjectRepository projectRepository) {
        super.setBaseRepository(projectRepository);
    }

    @Override
    public List<Project> findAllByClassifyId(Long classify_id, Pageable pageable) {
        return projectRepository.findAllByClassifyId(classify_id, pageable);
    }
}
