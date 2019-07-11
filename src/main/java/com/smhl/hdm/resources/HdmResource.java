package com.smhl.hdm.resources;

/**
 * A parent class for all data transfer objects. All DTOs should have an accompanying entity. In this case
 * a DTO is a class/object that contains entity information and is meant to be passed to the facade layer
 * for use in a controller and subsequently, the front-end
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
public interface HdmResource {

    /**
     * Determines whether the resource contains all necessary attributes. Used in place of a null check
     *
     * @return true if the resource contains all of its necessary attribute values
     */
    boolean isPresent();
}
