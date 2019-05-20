package com.smhl.hdm.repositories.participant.goalie;

import com.smhl.hdm.models.participant.impl.Goalie;
import com.smhl.hdm.repositories.HdmRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * DAO access-layer for goalies
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
public interface GoalieRepository extends HdmRepository, CrudRepository<Goalie, Long> {

    List<Goalie> getAllByActive(boolean isActive);
}
