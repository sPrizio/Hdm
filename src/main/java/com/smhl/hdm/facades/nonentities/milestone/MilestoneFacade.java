package com.smhl.hdm.facades.nonentities.milestone;

import com.smhl.hdm.models.nonentities.Milestone;

/**
 * Facade layer for milestones
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
public interface MilestoneFacade {

    /**
     * Obtains a milestone for the given stat and entity. The id used to find the entity in the system
     *
     * @param stat attribute on which we wish to calculate the milestone
     * @param id entity id
     * @return a milestone for the given stat attribute for the associated entity
     */
    Milestone obtainMilestone(String stat, Long id);
}
