package com.smhl.hdm.repositories.participant;

import com.smhl.hdm.models.participant.impl.Team;
import com.smhl.hdm.repositories.HdmRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * DAO access-layer for teams
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
public interface TeamRepository extends HdmRepository, CrudRepository<Team, Long> {

    List<Team> getAllByActive(boolean isActive);
}
