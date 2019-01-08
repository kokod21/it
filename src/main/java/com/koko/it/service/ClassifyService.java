package com.koko.it.service;

import com.koko.it.entity.Blog;
import com.koko.it.entity.Classify;

import java.util.List;
import java.util.Map;

public interface ClassifyService extends BaseService<Classify, Long> {
    List<Map<String, Object>> findAllClassify();
}
