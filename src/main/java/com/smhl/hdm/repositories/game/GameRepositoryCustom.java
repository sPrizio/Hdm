package com.smhl.hdm.repositories.game;

import com.smhl.hdm.models.entities.details.participant.impl.GoalieGameDetails;
import com.smhl.hdm.models.entities.details.participant.impl.SkaterGameDetails;

import java.util.List;

/**
 * Custom DAO for games
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
public interface GameRepositoryCustom {

    List<SkaterGameDetails> findSkaterGameDetails(String seasonString, Long id, Integer limit);

    List<GoalieGameDetails> findGoalieGameDetails(String seasonString, Long id, Integer limit);
}
