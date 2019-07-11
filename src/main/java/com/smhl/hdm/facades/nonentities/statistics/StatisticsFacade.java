package com.smhl.hdm.facades.nonentities.statistics;

import com.smhl.hdm.models.nonentities.Statistic;

/**
 * Facade layer for statistics
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
public interface StatisticsFacade {

    /**
     * Calculates an entity's statistics
     *
     * @param id entity id
     * @return a statistical object that houses typical statistics for each entity stat attribute category
     */
    Statistic obtainStatistics(Long id);

    /**
     * Calculates statistics for the various stat categories for the league for the given season
     *
     * @param seasonString season that we're looking at
     * @return a statistical object that houses typical statistics for each entity stat attribute category for the season and league
     */
    Statistic obtainActiveStatistics(String seasonString);
}
