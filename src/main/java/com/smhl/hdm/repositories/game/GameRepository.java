package com.smhl.hdm.repositories.game;

import com.smhl.hdm.models.entities.game.Game;
import com.smhl.hdm.repositories.HdmRepository;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * DAO access-layer for Game entities
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
public interface GameRepository extends HdmRepository, GameRepositoryCustom, CrudRepository<Game, Long> {

    /**
     * Finds games by season string and orders them by most recent games
     *
     * @param seasonString season we wish to look at
     * @return list of games, sorted by game time
     */
    List<Game> findBySeasonStringOrderByGameTimeDesc(final String seasonString);

    /**
     * Used typically for finding the most recently completed game, we search for the first game in a list of games
     * sorted by their game time in a descending order and that have a matching status
     *
     * @param seasonString season we wish to look at
     * @param gameStatus status of the game, typically used as COMPLETE
     * @return the most recent game for the given game status
     */
    Game findFirstBySeasonStringAndGameStatusOrderByGameTimeDesc(final String seasonString, final String gameStatus);

    /**
     * Finds a game by its game time. Game time is usually assumed to be unique
     *
     * @param gameTime given game time that we want to find
     * @return game with the matching game t ime
     */
    Game findByGameTime(final LocalDateTime gameTime);
}
