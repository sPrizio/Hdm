package com.smhl.hdm.service.nonentities.statistic;

import com.smhl.hdm.models.entities.participant.Participant;
import com.smhl.hdm.models.nonentities.Statistic;

import java.util.List;

/**
 * Parent-level statistic interface for obtaining statistic for a particular entity
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
public interface StatisticsService<P extends Participant> {

    /**
     * Calculates the statistics for a participant's seasonal stats
     *
     * @param entity participant that we're looking at
     * @return a statistic object used to obtain statistical measures for a participant's stats
     */
    Statistic calculate(P entity);

    /**
     * Calculates the statistics for a group of entities/participants for a season. These refer to league averages for goals, saves, etc.
     *
     * @param entities list of participants that we're looking at
     * @param seasonString season that we're looking at
     * @return a statistic object with statistical markers for the season as a whole
     */
    Statistic calculate(List<P> entities, String seasonString);
}
