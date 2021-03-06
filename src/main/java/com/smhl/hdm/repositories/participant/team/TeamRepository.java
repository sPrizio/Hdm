package com.smhl.hdm.repositories.participant.team;

import com.smhl.hdm.models.entities.participant.impl.Team;
import com.smhl.hdm.repositories.HdmRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * DAO access-layer for teams
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
public interface TeamRepository extends HdmRepository, TeamRepositoryCustom, CrudRepository<Team, Long> {
}
