package com.smhl.hdm.repositories.participant.team;

import com.smhl.hdm.models.participant.impl.Team;

import java.util.List;

/**
 * Custom DAO access-layer for Teams
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
public interface TeamRepositoryCustom {

    List<Team> findBySeasonString(String seasonString);

    List<Team> findBySeasonStringSorted(String seasonString, String field, String order);
}
