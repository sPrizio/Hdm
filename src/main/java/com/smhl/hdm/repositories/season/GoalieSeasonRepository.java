package com.smhl.hdm.repositories.season;

import com.smhl.hdm.models.entities.season.impl.GoalieSeason;
import com.smhl.hdm.repositories.HdmRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * DAO access-layer for GoalieSeasons
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
public interface GoalieSeasonRepository extends HdmRepository, CrudRepository<GoalieSeason, Long> {
}
