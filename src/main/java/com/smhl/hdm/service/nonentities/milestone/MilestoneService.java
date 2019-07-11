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

    /**
     * Given the stat and entity, calculate the milestone (plateau) for the stat and entity. We use this method
     * to obtain a milestone object that contains the entity's stat value and the next target that they're going to reach
     *
     * @param stat category on which we're calculating the milestone
     * @param entity entity that we're looking at
     * @return a milestone object
     */
    Milestone calculateMilestone(String stat, P entity);
}
