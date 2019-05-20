package com.smhl.hdm.resources.season;

import com.smhl.hdm.resources.HdmResource;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

/**
 * A DTO for skater seasons
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@NoArgsConstructor
public class SkaterSeasonResource implements HdmResource {

    @Getter
    @Setter
    private String seasonString;

    @Getter
    @Setter
    private int gamesPlayed;

    @Getter
    @Setter
    private int goals;

    @Getter
    @Setter
    private int assists;

    @Getter
    @Setter
    private int points;

    @Getter
    @Setter
    private double pointsPerGame;

    @Getter
    @Setter
    private int shots;

    @Getter
    @Setter
    private int blockedShots;


    @Override
    public boolean isPresent() {
        return StringUtils.isNotEmpty(this.seasonString);
    }
}
