package com.smhl.hdm.repositories.participant;

import com.smhl.hdm.models.participant.impl.Skater;
import com.smhl.hdm.repositories.HdmRepository;
import com.smhl.hdm.repositories.participant.skater.SkaterRepositoryCustom;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * DAO access-layer for Skaters
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
public interface SkaterRepository extends HdmRepository, SkaterRepositoryCustom, CrudRepository<Skater, Long> {

    List<Skater> getAllByActive(boolean isActive);
}
