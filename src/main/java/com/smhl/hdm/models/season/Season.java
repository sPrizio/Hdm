package com.smhl.hdm.models.season;

import com.smhl.hdm.models.HdmEntity;

/**
 * Represents a season, a unit of time that represents a collection of games that a participant was involved in for a give period of time
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
public interface Season extends HdmEntity {

    String getSeasonString();
}
