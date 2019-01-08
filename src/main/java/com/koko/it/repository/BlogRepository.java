package com.koko.it.repository;

import com.koko.it.entity.Blog;
import org.springframework.stereotype.Repository;


@Repository
public interface BlogRepository extends BaseRepository<Blog, Long> {

}
