package com.smhl.hdm.repositories.game;

import com.smhl.hdm.models.game.Game;
import com.smhl.hdm.repositories.HdmRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * DAO access-layer for Game
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
public interface GameRepository extends HdmRepository, CrudRepository<Game, Long> {
}
