package com.smhl.hdm.repositories.participant.team;

import com.smhl.hdm.models.entities.participant.impl.Team;

import java.util.List;

/**
 * Custom DAO access-layer for Teams. We define custom methods for functionality that cannot be handled by the OOTB spring repository system
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
public interface TeamRepositoryCustom {

    /**
     * Finds all teams that have a season matching the given season string
     *
     * @param seasonString season that we're looking at
     * @return list of teams for the given season
     */
    List<Team> findBySeasonString(String seasonString);

    /**
     * Finds all teams that have a season matching the given season string, sorted by a given attribute and order
     *
     * @param seasonString season that we're looking at
     * @return list of teams for the given season, sorted by stat
     */
    List<Team> findBySeasonStringSorted(String seasonString, String field, String order);
}
