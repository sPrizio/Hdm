package com.smhl.hdm.models.entities.participant;

import com.smhl.hdm.models.entities.HdmEntity;
import com.smhl.hdm.models.entities.season.Season;

import java.util.Set;

/**
 * A parent for all entities that take part in a game
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
public interface Participant<S extends Season> extends HdmEntity {

    S getCurrentSeason();

    Set<S> getSeasons();

    S getSeasonForSeasonString(String seasonString);

    String getName();
}
