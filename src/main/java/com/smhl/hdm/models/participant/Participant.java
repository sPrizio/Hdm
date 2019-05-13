package com.smhl.hdm.models.participant;

import com.smhl.hdm.models.HdmEntity;
import com.smhl.hdm.models.season.Season;

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
