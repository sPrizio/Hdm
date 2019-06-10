package com.smhl.hdm.repositories.game;

import com.smhl.hdm.models.entities.details.participant.GoalieGameDetails;
import com.smhl.hdm.models.entities.details.participant.SkaterGameDetails;

import java.util.List;

/**
 * Custom DAO for games
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
public interface GameRepositoryCustom {

    List<SkaterGameDetails> findSkaterGameDetails(String seasonString, Long id, int limit);

    List<GoalieGameDetails> findGoalieGameDetails(String seasonString, Long id, int limit);
}
