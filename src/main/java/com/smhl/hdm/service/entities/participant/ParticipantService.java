package com.smhl.hdm.service.entities.participant;

import com.smhl.hdm.models.entities.details.Details;
import com.smhl.hdm.models.entities.participant.Participant;
import com.smhl.hdm.service.entities.HdmService;

import java.util.List;

/**
 * Parent-level interface for all participant service classes
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
public interface ParticipantService<P extends Participant, D extends Details> extends HdmService<P> {

    void updateStats(D details);

    List<P> getAllParticipantsForSeason(String seasonString, String field, String order);
}
