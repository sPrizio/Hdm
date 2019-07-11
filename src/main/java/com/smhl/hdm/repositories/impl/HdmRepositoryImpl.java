package com.smhl.hdm.repositories.impl;

import com.smhl.hdm.models.entities.HdmEntity;
import com.smhl.hdm.repositories.HdmRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 * Implementation of the Hdm Repository contract
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@Repository
public class HdmRepositoryImpl implements HdmRepository {

    @PersistenceContext
    private EntityManager entityManager;


    //  METHODS

    /**
     * Refreshes an hdmEntity
     *
     * @param hdmEntity hdm that will have it's data refreshed
     */
    @Transactional
    public void refresh(HdmEntity hdmEntity) {
        this.entityManager.refresh(this.entityManager.merge(hdmEntity));
    }
}
