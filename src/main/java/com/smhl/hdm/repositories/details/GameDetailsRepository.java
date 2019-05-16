package com.smhl.hdm.repositories.details;

import com.smhl.hdm.models.details.game.GameDetails;
import com.smhl.hdm.repositories.HdmRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * DAO access-layer for Game Details
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
public interface GameDetailsRepository extends HdmRepository, CrudRepository<GameDetails, Long> {
}
