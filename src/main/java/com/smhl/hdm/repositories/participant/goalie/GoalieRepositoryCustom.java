package com.smhl.hdm.repositories.participant.goalie;

import com.smhl.hdm.models.entities.participant.impl.Goalie;

import java.util.List;

/**
 * Custom DAO access-layer for goalies
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
public interface GoalieRepositoryCustom {

    List<Goalie> findBySeasonString(String seasonString);

    List<Goalie> findBySeasonStringSorted(String seasonString, String field, String order);

    List<Goalie> findTopGoaliesForStatAndLimit(String stat, Integer limit);
}
