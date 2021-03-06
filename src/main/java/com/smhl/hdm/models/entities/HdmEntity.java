package com.smhl.hdm.models.entities;

/**
 * A parent to all entities contained within this system. Entities are objects that are stored in the database, known as models
 * All entities should have a Resource-equivalent class used as a DTO between controller endpoints
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
public interface HdmEntity {

    /**
     * Get the id of the entity, the id us a unique identifier for the object
     *
     * @return Long id
     */
    Long getId();
}
