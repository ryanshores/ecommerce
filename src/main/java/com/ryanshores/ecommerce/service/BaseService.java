package com.ryanshores.ecommerce.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public class BaseService<T> {

    private final JpaRepository<T, Long> repo;

    public BaseService(JpaRepository<T, Long> repo) {
        this.repo = repo;
    }

    public List<T> getAll() {
        return repo.findAll();
    }

    public T findById(Long id) throws EntityNotFoundException {
        return repo.findById(id).orElseThrow(() -> new EntityNotFoundException(id.toString()));
    }

    public T save(T entity) {
        return repo.save(entity);
    }
}
