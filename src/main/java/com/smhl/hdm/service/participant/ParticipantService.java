package com.smhl.hdm.service.participant;

import com.smhl.hdm.models.details.Details;
import com.smhl.hdm.models.participant.Participant;
import com.smhl.hdm.service.HdmService;

/**
 * Parent-level interface for all participant service classes
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
public interface ParticipantService<P extends Participant, D extends Details> extends HdmService<P> {

    void updateStats(D details);
}
