package com.smhl.hdm.repositories.game;

import com.smhl.hdm.models.entities.game.Game;
import com.smhl.hdm.repositories.HdmRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * DAO access-layer for Game
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
public interface GameRepository extends HdmRepository, GameRepositoryCustom, CrudRepository<Game, Long> {

    List<Game> findBySeasonStringOrderByGameTimeDesc(final String seasonString);
}
