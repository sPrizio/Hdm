package com.smhl.hdm.repositories.participant.skater;

import com.smhl.hdm.models.entities.participant.impl.Skater;

import java.util.List;

/**
 * Custom repository methods for skaters
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
public interface SkaterRepositoryCustom {

    List<Skater> findBySeasonString(String seasonString);

    List<Skater> findBySeasonStringSorted(String seasonString, String field, String order);

    List<Skater> findTopSkatersForStatAndLimit(String stat, Integer limit);
}
