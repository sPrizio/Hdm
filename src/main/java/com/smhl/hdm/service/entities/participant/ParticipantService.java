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

    /**
     * Updates a participant's season stats with information from the given details
     *
     * @param details game details for the participant
     */
    void updateStats(D details);

    /**
     * Finds all of the participants for a given season, ordered by a specific stat category and ordered
     *
     * @param seasonString season that we're looking at
     * @param field stat field we want to sort the participants by
     * @param order sort order, asc or desc
     * @return a list of participants sorted
     */
    List<P> getAllParticipantsForSeason(String seasonString, String field, String order);
}
