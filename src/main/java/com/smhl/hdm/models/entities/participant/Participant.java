package com.smhl.hdm.models.entities.participant;

import com.smhl.hdm.models.entities.HdmEntity;
import com.smhl.hdm.models.entities.season.Season;

import java.util.Set;

/**
 * A participant is an entity that operates in a game, i.e. performs or plays in a game
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
public interface Participant<S extends Season> extends HdmEntity {

    /**
     * Gets the season for a participant that matches the system's current season
     *
     * @return season that matches the current season of the system
     */
    S getCurrentSeason();

    /**
     * Gets a collection of all seasons for the participant
     *
     * @return a set of seasons that the participant has completed/initiated
     */
    Set<S> getSeasons();

    /**
     * Gets the season that matches the given season string
     *
     * @param seasonString season string that we want to look at
     * @return season matching the given season string
     */
    S getSeasonForSeasonString(String seasonString);

    /**
     * Gets the participant's name
     *
     * @return name of the participant
     */
    String getName();

    /**
     * Adds a season to this participants list of seasons
     *
     * @param season season to be added
     */
    void addSeason(S season);
}
