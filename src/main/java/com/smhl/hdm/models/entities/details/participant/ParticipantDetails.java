package com.smhl.hdm.models.entities.details.participant;

import com.smhl.hdm.models.entities.details.Details;
import com.smhl.hdm.models.entities.participant.Participant;

/**
 * Generic class for details for participant
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
public interface ParticipantDetails<P extends Participant> extends Details {

    /**
     * Returns the participant for use as a getter
     *
     * @return participant
     */
    P getParticipant();
}
