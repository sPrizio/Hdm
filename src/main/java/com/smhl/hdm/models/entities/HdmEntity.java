package com.smhl.hdm.models.entities;

/**
 * A parent to all entities contained within this system. All entities should have a Resource-equivalent class
 * used as a DTO between controller endpoints
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
public interface HdmEntity {

    Long getId();
}
