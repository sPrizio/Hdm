package com.smhl.hdm.repositories.participant.skater;

import com.smhl.hdm.models.entities.participant.impl.Skater;

import java.util.List;

/**
 * Custom repository methods for skaters. We define custom methods for functionality that cannot be handled by the OOTB spring repository system
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
public interface SkaterRepositoryCustom {

    /**
     * Finds all skaters that have a season matching the given season string
     *
     * @param seasonString season that we're looking at
     * @return list of skaters for the given season
     */
    List<Skater> findBySeasonString(String seasonString);

    /**
     * Finds all skaters that have a season matching the given season string, sorted by a given attribute and order
     *
     * @param seasonString season that we're looking at
     * @return list of skaters for the given season, sorted by stat
     */
    List<Skater> findBySeasonStringSorted(String seasonString, String field, String order);

    /**
     * Finds skaters for a given stat, ordered in descending fashion
     *
     * @param stat stat we wish to sort skaters by
     * @param limit number of results we wish to obtain
     * @return a list of skaters sorted by a particular stat (descending order)
     */
    List<Skater> findTopSkatersForStatAndLimit(String stat, Integer limit);
}
