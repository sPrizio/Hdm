package com.smhl.hdm.facades.nonentities.milestone;

import com.smhl.hdm.models.nonentities.Milestone;

/**
 * Facade layer for milestones
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
public interface MilestoneFacade {

    Milestone obtainMilestone(String stat, Long id);
}
