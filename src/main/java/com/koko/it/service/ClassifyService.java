package com.koko.it.service;

import com.koko.it.entity.Classify;

import java.util.List;
import java.util.Map;

public interface ClassifyService extends BaseService<Classify, Long> {
    List<Classify> findByParentIdNot(Long parentId);

    List<Classify> findByParentId(Long parentId);

    void deleteClassifyById(Long id, Long parentId);
}
