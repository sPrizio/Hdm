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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        SkaterSeasonResource that = (SkaterSeasonResource) o;
        return
                this.gamesPlayed == that.gamesPlayed &&
                this.goals == that.goals &&
                this.assists == that.assists &&
                this.points == that.points &&
                Double.compare(that.pointsPerGame, this.pointsPerGame) == 0 &&
                this.shots == that.shots &&
                this.blockedShots == that.blockedShots &&
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
