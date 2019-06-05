package com.smhl.hdm.facades.entities;

import com.smhl.hdm.resources.HdmResource;

import java.util.List;

/**
 * Facades are classes that return resource objects for entities. They are meant to expose data to the front-end
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
public interface HdmFacade<R extends HdmResource> {

    R find(Long id);

    List<R> findAll();
}
