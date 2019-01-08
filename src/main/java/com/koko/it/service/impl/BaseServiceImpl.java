package com.koko.it.service.impl;

import com.koko.it.repository.BaseRepository;
import com.koko.it.service.BaseService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;


@Transactional
public class BaseServiceImpl<T, ID extends Serializable> implements BaseService<T,ID> {


    private BaseRepository<T, ID> baseRepository;

    public BaseRepository<T, ID> getBaseRepository() {
        return baseRepository;
    }

    public void setBaseRepository(BaseRepository<T, ID> baseRepository) {
        this.baseRepository = baseRepository;
    }

    public <S extends T> S save(S var1) {
        return baseRepository.save(var1);
    }

    public Optional<T> findById(ID var1) {
        return baseRepository.findById(var1);
    }

    public boolean existsById(ID var1) {
        return baseRepository.existsById(var1);
    }

    public long count() {
        return baseRepository.count();
    }

    public void deleteById(ID var1) {
        baseRepository.deleteById(var1);
    }

    public void delete(T var1) {
        baseRepository.delete(var1);
    }

    public void deleteAll(Iterable<? extends T> var1) {
        baseRepository.deleteAll(var1);
    }

    public void deleteAll() {
        baseRepository.deleteAll();
    }


    public List<T> findAll() {
        return baseRepository.findAll();
    }

    public List<T> findAll(Sort var1) {
        return baseRepository.findAll(var1);
    }

    public List<T> findAllById(Iterable<ID> var1) {
        return baseRepository.findAllById(var1);
    }

    public <S extends T> List<S> saveAll(Iterable<S> var1) {
        return baseRepository.saveAll(var1);
    }

    public void flush() {
        baseRepository.flush();
    }

    public <S extends T> S saveAndFlush(S var1) {
        return baseRepository.saveAndFlush(var1);
    }

    public void deleteInBatch(Iterable<T> var1) {
        baseRepository.deleteInBatch(var1);
    }

    public void deleteAllInBatch() {
        baseRepository.deleteAllInBatch();
    }

    public T getOne(ID var1) {
        return baseRepository.getOne(var1);
    }

    public <S extends T> List<S> findAll(Example<S> var1) {
        return baseRepository.findAll(var1);
    }

    public <S extends T> List<S> findAll(Example<S> var1, Sort var2) {
        return baseRepository.findAll(var1, var2);
    }

    public Page<T> findAll(Pageable var1) {
        return baseRepository.findAll(var1);
    }
}
