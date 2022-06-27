package com.codegym.test.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IGeneralService<T> {
    Page<T> findAll(Pageable pageable);

    Iterable<T> findAllNoPage();

    Optional<T> findById(Long id);

    T save(T t);

    void remove(Long id);
}
