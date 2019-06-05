package com.smhl.hdm.service.entities;

import com.smhl.hdm.models.entities.HdmEntity;

import java.util.List;
import java.util.Optional;

/**
 * Parent-level Service class for all system services to follow
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
public interface HdmService<E extends HdmEntity> {

    void refresh(E entity);

    Optional<E> find(Long id);

    List<E> findAll();

    E save(E entity);

    void delete(Long id);
}
