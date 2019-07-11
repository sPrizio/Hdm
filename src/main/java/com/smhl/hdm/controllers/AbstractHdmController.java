package com.smhl.hdm.controllers;

import com.smhl.hdm.controllers.response.HdmApiResponse;
import com.smhl.hdm.enums.HdmApiResponseResult;
import com.smhl.hdm.exceptions.HdmEntityNotFoundException;
import com.smhl.hdm.resources.HdmResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * A parent class that holds common variables and methods for API controllers
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
public abstract class AbstractHdmController<R extends HdmResource> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractHdmController.class);


    //  METHODS

    /**
     * A generic find method that will determine whether the entity could be found
     *
     * @param id                  id of entity
     * @param resource participant as a result of calling find()
     * @return response based on whether the entity was found
     */
    protected ResponseEntity<HdmApiResponse> findEntity(Long id, R resource) {
        try {
            if (resource.isPresent()) {
                return new ResponseEntity<>(new HdmApiResponse(HdmApiResponseResult.SUCCESS, resource), HttpStatus.OK);
            } else {
                throw new HdmEntityNotFoundException("The selected entity could not be found.");
            }
        } catch (HdmEntityNotFoundException e) {
            LOGGER.error("Entity with id {} could not be found", id);
            return new ResponseEntity<>(new HdmApiResponse(HdmApiResponseResult.FAILURE, e.getMessage()), HttpStatus.OK);
        }
    }
}
