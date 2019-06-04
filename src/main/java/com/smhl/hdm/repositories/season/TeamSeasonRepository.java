package com.smhl.hdm.repositories.season;

import com.smhl.hdm.models.entities.season.impl.TeamSeason;
import com.smhl.hdm.repositories.HdmRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * Dao access-layer for TeamSeasons
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
public interface TeamSeasonRepository extends HdmRepository, CrudRepository<TeamSeason, Long> {
}
