package com.ryanshores.ecommerce.service;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public class BaseService<T> {

    private final JpaRepository<T, Long> repo;

    public BaseService(JpaRepository<T, Long> repo) {
        this.repo = repo;
    }

    public List<T> getAll() {
        return repo.findAll();
    }

    public Optional<T> findById(Long id) {
        return repo.findById(id);
    }

    public T save(T entity) {
        return repo.save(entity);
    }
}
