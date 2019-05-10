package com.smhl.hdm.repositories.season;

import com.smhl.hdm.models.season.impl.SkaterSeason;
import com.smhl.hdm.repositories.HdmRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * DAO access-layer for SkaterSeasons
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
public interface SkaterSeasonRepository extends HdmRepository, CrudRepository<SkaterSeason, Long> {
}
