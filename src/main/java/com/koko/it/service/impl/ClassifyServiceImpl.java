package com.koko.it.service.impl;

import com.koko.it.entity.Blog;
import com.koko.it.entity.Classify;
import com.koko.it.repository.BlogRepository;
import com.koko.it.repository.ClassifyRepository;
import com.koko.it.service.BlogService;
import com.koko.it.service.ClassifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ClassifyServiceImpl extends BaseServiceImpl<Classify, Long> implements ClassifyService {

    @Autowired
    private ClassifyRepository classifyRepository;

    @Autowired
    public void setBaseRepository(ClassifyRepository classifyRepository) {
        super.setBaseRepository(classifyRepository);
    }

    @Override
    public List<Map<String, Object>> findAllClassify() {
        return classifyRepository.findAllClassify();
    }
}
