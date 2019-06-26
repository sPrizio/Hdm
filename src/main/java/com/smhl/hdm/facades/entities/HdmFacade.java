package com.smhl.hdm.facades.entities;

import com.smhl.hdm.resources.HdmResource;

import java.util.List;
import java.util.Map;

/**
 * Facades are classes that return resource objects for entities. They are meant to expose data to the front-end.
 * All operations are performed on entity classes, and resources that are returned are entities that are transformed
 * into resources via converters
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
public interface HdmFacade<R extends HdmResource> {

    /**
     * Finds an entity for the given id
     *
     * @param id id of the entity
     * @return resource representation of the entity created via converter
     */
    R find(Long id);

    /**
     * Finds all entities and converts them to a list of resources
     *
     * @return list of resources
     */
    List<R> findAll();

    /**
     * Creates a new entity from a map of parameters and returns the resource of the created entity
     *
     * @param params input parameters taken from a controller endpoint, at this stage they have been validated
     * @return newly created resource based off the newly created entity
     */
    R create(Map<String, Object> params);

    /**
     * Deletes an entity by the given id
     *
     * @param id entity id used for model deletion
     */
    void delete(Long id);
}
