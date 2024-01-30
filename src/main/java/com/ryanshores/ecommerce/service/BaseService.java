package com.ryanshores.ecommerce.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public class BaseService<T> {

    private final Class<T> classDetails;
    private final JpaRepository<T, Long> repo;
    private final Logger logger;

    public BaseService(JpaRepository<T, Long> repo, Class<T> classDetails) {
        this.repo = repo;
        this.classDetails = classDetails;
        this.logger = LoggerFactory.getLogger(BaseService.class);
    }

    private String getClassName() {
        return classDetails.getName();
    }

    public List<T> getAll() {
        return repo.findAll();
    }

    public T findById(Long id) throws Exception {
        var object = repo.findById(id);
        if (object.isEmpty()) {
            var errorMessage = getClassName() + " not found by id";
            logger.debug(errorMessage + "::" + id);
            throw new Exception(errorMessage);
        }
        return object.get();
    }

    public T save(T entity) {
        return repo.save(entity);
    }
}
