package com.smhl.hdm.repositories.game;

import com.smhl.hdm.models.entities.details.participant.impl.GoalieGameDetails;
import com.smhl.hdm.models.entities.details.participant.impl.SkaterGameDetails;

import java.util.List;

/**
 * Custom DAO for games. We define custom methods for functionality that cannot be handled by the OOTB spring repository system
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
public interface GameRepositoryCustom {

    /**
     * Finds a list of skater game details for the given season, game and limits the number of results
     *
     * @param seasonString season we wish to look at
     * @param id game id
     * @param limit how many results we want
     * @return a list of game details for skaters
     */
    List<SkaterGameDetails> findSkaterGameDetails(String seasonString, Long id, Integer limit);

    /**
     * Finds a list of goalie game details for the given season, game and limits the number of results
     *
     * @param seasonString season we wish to look at
     * @param id game id
     * @param limit how many results we want
     * @return a list of game details for goalies
     */
    List<GoalieGameDetails> findGoalieGameDetails(String seasonString, Long id, Integer limit);
}
