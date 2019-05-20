package com.smhl.hdm.controllers;

import com.smhl.hdm.controllers.response.HdmApiResponse;
import com.smhl.hdm.enums.HdmApiResponseResult;
import com.smhl.hdm.exceptions.HdmEntityNotFoundException;
import com.smhl.hdm.resources.participant.ParticipantResource;
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
public abstract class AbstractHdmController<P extends ParticipantResource> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractHdmController.class);

    /**
     * A generic find method that will determine whether the participant could be found
     *
     * @param id                  id of participant
     * @param participantResource participant as a result of calling find()
     * @return response based on whether the participant was found
     */
    protected ResponseEntity<HdmApiResponse> findParticipant(Long id, P participantResource) {
        try {
            if (participantResource.isPresent()) {
                return new ResponseEntity<>(new HdmApiResponse(HdmApiResponseResult.SUCCESS, participantResource), HttpStatus.OK);
            } else {
                throw new HdmEntityNotFoundException("The selected participant could not be found.");
            }
        } catch (HdmEntityNotFoundException e) {
            LOGGER.error("Participant with id {} could not be found", id);
            return new ResponseEntity<>(new HdmApiResponse(HdmApiResponseResult.FAILURE, e.getMessage()), HttpStatus.OK);
        }
    }
}
