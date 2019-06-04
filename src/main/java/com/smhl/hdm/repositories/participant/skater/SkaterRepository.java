package com.smhl.hdm.repositories.participant.skater;

import com.smhl.hdm.models.entities.participant.impl.Skater;
import com.smhl.hdm.repositories.HdmRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * DAO access-layer for Skaters
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
public interface SkaterRepository extends HdmRepository, SkaterRepositoryCustom, CrudRepository<Skater, Long> {
}
