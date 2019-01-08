package com.koko.it.repository;

import com.koko.it.entity.GithubProject;
import org.springframework.stereotype.Repository;


@Repository
public interface GithubProjectRepository extends BaseRepository<GithubProject, Long> {

}
