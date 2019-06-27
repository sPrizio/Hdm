package com.smhl.hdm.resources.season;

import com.smhl.hdm.resources.HdmResource;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * A DTO for skater seasons
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@NoArgsConstructor
public class SkaterSeasonResource implements HdmResource, Comparable<SkaterSeasonResource> {

    @Getter
    @Setter
    private String seasonString;

    @Getter
    @Setter
    private Integer gamesPlayed;

    @Getter
    @Setter
    private Integer goals;

    @Getter
    @Setter
    private Integer assists;

    @Getter
    @Setter
    private Integer points;

    @Getter
    @Setter
    private Double pointsPerGame;

    @Getter
    @Setter
    private Integer shots;

    @Getter
    @Setter
    private Integer blockedShots;


    //  METHODS

    @Override
    public boolean isPresent() {
        return StringUtils.isNotEmpty(this.seasonString);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        SkaterSeasonResource that = (SkaterSeasonResource) o;
        return
                this.gamesPlayed.equals(that.gamesPlayed) &&
                this.goals.equals(that.goals) &&
                this.assists.equals(that.assists) &&
                this.points.equals(that.points) &&
                Double.compare(that.pointsPerGame, this.pointsPerGame) == 0 &&
                this.shots.equals(that.shots) &&
                this.blockedShots.equals(that.blockedShots) &&
                this.seasonString.equals(that.seasonString);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.seasonString, this.gamesPlayed, this.goals, this.assists, this.points, this.pointsPerGame, this.shots, this.blockedShots);
    }

    @Override
    public int compareTo(SkaterSeasonResource o) {
        return this.seasonString.compareTo(o.seasonString);
    }
}
