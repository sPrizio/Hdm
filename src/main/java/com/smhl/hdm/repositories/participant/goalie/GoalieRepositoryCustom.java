package com.smhl.hdm.repositories.participant.goalie;

import com.smhl.hdm.models.entities.participant.impl.Goalie;

import java.util.List;

/**
 * Custom DAO access-layer for goalies. We define custom methods for functionality that cannot be handled by the OOTB spring repository system
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
public interface GoalieRepositoryCustom {

    /**
     * Finds all goalies that have a season matching the given season string
     *
     * @param seasonString season that we're looking at
     * @return list of goalies for the given season
     */
    List<Goalie> findBySeasonString(String seasonString);

    /**
     * Finds all goalies that have a season matching the given season string, sorted by a given attribute and order
     *
     * @param seasonString season that we're looking at
     * @return list of goalies for the given season, sorted by stat
     */
    List<Goalie> findBySeasonStringSorted(String seasonString, String field, String order);

    /**
     * Finds goalies for a given stat, ordered in descending fashion
     *
     * @param stat stat we wish to sort goalies by
     * @param limit number of results we wish to obtain
     * @return a list of goalies sorted by a particular stat (descending order)
     */
    List<Goalie> findTopGoaliesForStatAndLimit(String stat, Integer limit);
}
