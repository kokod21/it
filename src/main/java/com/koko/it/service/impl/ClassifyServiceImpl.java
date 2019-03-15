package com.koko.it.service.impl;

import com.koko.it.entity.Classify;
import com.koko.it.repository.ClassifyRepository;
import com.koko.it.service.ClassifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ClassifyServiceImpl extends BaseServiceImpl<Classify, Long> implements ClassifyService {

    @Autowired
    private ClassifyRepository classifyRepository;

    @Autowired
    public void setBaseRepository(ClassifyRepository classifyRepository) {
        super.setBaseRepository(classifyRepository);
    }

    @Override
    public List<Classify> findByParentIdNot(Long parentId) {
        return classifyRepository.findByParentIdNot(parentId);
    }

    @Override
    public List<Classify> findByParentId(Long parentId) {
        return classifyRepository.findByParentId(parentId);
    }

    @Override
    public void deleteClassifyById(Long id, Long parentId) {
        classifyRepository.deleteClassifyById(id, parentId);
    }
}
