package com.smhl.hdm.models.entities.season;

import com.smhl.hdm.models.entities.HdmEntity;

/**
 * Represents a season, a unit of time that represents a collection of games that a participant was involved in for a give period of time
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
public interface Season extends HdmEntity {

    /**
     * Get the season string that this season belongs to
     *
     * @return season string ex: 2018-2019
     */
    String getSeasonString();
}
