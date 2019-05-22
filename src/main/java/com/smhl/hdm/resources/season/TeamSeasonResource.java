package com.smhl.hdm.resources.season;

import com.smhl.hdm.resources.HdmResource;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

/**
 * DTO for team seasons
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@NoArgsConstructor
public class TeamSeasonResource implements HdmResource {

    @Getter
    @Setter
    private String seasonString;

    @Getter
    @Setter
    private int gamesPlayed;

    @Getter
    @Setter
    private int wins;

    @Getter
    @Setter
    private int losses;

    @Getter
    @Setter
    private int ties;

    @Getter
    @Setter
    private int points;

    @Getter
    @Setter
    private int goalsFor;

    @Getter
    @Setter
    private int goalsAgainst;

    @Getter
    @Setter
    private int differential;

    @Override
    public boolean isPresent() {
        return StringUtils.isNotEmpty(this.seasonString);
    }
}
