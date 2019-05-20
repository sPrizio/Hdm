package com.smhl.hdm.facades.participant;

import com.smhl.hdm.facades.HdmFacade;
import com.smhl.hdm.resources.participant.ParticipantResource;

import java.util.List;

/**
 * Facade for participants
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
public interface ParticipantFacade<R extends ParticipantResource> extends HdmFacade<R> {

    List<R> findAllParticipantsForSeason(String seasonString, String field, String order);
}
