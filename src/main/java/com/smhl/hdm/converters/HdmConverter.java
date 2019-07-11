package com.smhl.hdm.converters;

import com.smhl.hdm.models.entities.HdmEntity;
import com.smhl.hdm.resources.HdmResource;

import java.util.List;

/**
 * A converter is a class that converts entities into dtos for use by API controllers
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
public interface HdmConverter<E extends HdmEntity, R extends HdmResource> {

    /**
     * Converts an entity (object stored the database) into a resource (a dto used by facades)
     *
     * @param entity entity to convert
     * @return newly created resource based off of the entity
     */
    R convert(E entity);

    /**
     * Converts a list of entities into a list of resources
     *
     * @param entity list of entities to convert
     * @return list of resources based off of their respective entities
     */
    List<R> convertAll(List<E> entity);
}
