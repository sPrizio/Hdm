package com.smhl.hdm.service.nonentities.milestone;

import com.smhl.hdm.models.entities.participant.Participant;
import com.smhl.hdm.models.nonentities.Milestone;

/**
 * Interface blueprint for the Milestone service
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
public interface MilestoneService<P extends Participant> {

    Milestone calculateMilestone(String stat, P entity);
}
