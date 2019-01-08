package com.koko.it.service.impl;

import com.koko.it.entity.Blog;
import com.koko.it.repository.BlogRepository;
import com.koko.it.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BlogServiceImpl extends BaseServiceImpl<Blog, Long> implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    public void setBaseRepository(BlogRepository blogRepository) {
        super.setBaseRepository(blogRepository);
    }

}
