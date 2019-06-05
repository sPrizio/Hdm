package com.smhl.hdm.facades.nonentities.statistics;

import com.smhl.hdm.models.nonentities.Statistic;

/**
 * Facade layer for statistics
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
public interface StatisticsFacade {

    Statistic obtainStatistics(Long id);

    Statistic obtainActiveStatistics(String seasonString);
}
