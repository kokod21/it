package com.koko.it.service.impl;

import com.koko.it.entity.GithubProject;
import com.koko.it.repository.GithubProjectRepository;
import com.koko.it.service.GithubProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class GithubServiceImpl extends BaseServiceImpl<GithubProject, Long> implements GithubProjectService {

    @Autowired
    private GithubProjectRepository githubProjectRepository;

    @Autowired
    public void setBaseRepository(GithubProjectRepository githubProjectRepository) {
        super.setBaseRepository(githubProjectRepository);
    }

}
