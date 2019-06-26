package com.smhl.hdm.facades.entities.participant;

import com.smhl.hdm.facades.entities.HdmFacade;
import com.smhl.hdm.resources.participant.ParticipantResource;

import java.util.List;

/**
 * Facade for participants. As usual, participants deal with season-specific logic usually and we
 * wish to capture that here
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
public interface ParticipantFacade<R extends ParticipantResource> extends HdmFacade<R> {

    /**
     * Finds and obtains all participants for the given season string and orders them by a specific field (attribute) and orders them
     * accordingly
     *
     * @param seasonString season on which we wish to look
     * @param field attribute on which we intend to sort the participants
     * @param order ascending or descending order for the sorting
     * @return a list of participants for the given season, sorted and ordered
     */
    List<R> findAllParticipantsForSeason(String seasonString, String field, String order);
}
