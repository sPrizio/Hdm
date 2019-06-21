package com.smhl.hdm.models.entities.season.impl;

import com.smhl.hdm.models.entities.season.Season;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

/**
 * Implementation of a season for a skater. Meant to track stats like goals, assists, points, etc.
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class SkaterSeason implements Season, Comparable<SkaterSeason> {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Column
    @NonNull
    private String seasonString;

    @Getter
    @Setter
    @Column
    @NonNull
    private Integer gamesPlayed;

    @Getter
    @Setter
    @Column
    @NonNull
    private Integer goals;

    @Getter
    @Setter
    @Column
    @NonNull
    private Integer assists;

    @Getter
    @Setter
    @Column
    @NonNull
    private Integer shots;

    @Getter
    @Setter
    @Column
    @NonNull
    private Integer blockedShots;


    //  METHODS

    /**
     * Returns a skater's points for the season
     *
     * @return goals summed with assists
     */
    public Integer getPoints() {
        return this.goals + this.assists;
    }

    /**
     * Returns the average number of points per game achieved for this season
     *
     * @return points divided by games played
     */
    public Double getPointsPerGame() {
        if (this.gamesPlayed > 0) {
            return Math.round(((double) getPoints() / (double) this.gamesPlayed) * 100.0) / 100.0;
        }

        return 0.0;
    }

    /**
     * Increments games player by 1
     */
    public void incrementGamesPlayed() {
        this.gamesPlayed += 1;
    }

    /**
     * Increments goals by specified increment amount
     *
     * @param increment amount to increment goals by
     */
    public void incrementGoals(Integer increment) {
        this.goals += increment;
    }

    /**
     * Increments assists by specified increment amount
     *
     * @param increment amount to increment assists by
     */
    public void incrementAssists(Integer increment) {
        this.assists += increment;
    }

    /**
     * Increments shots by specified increment amount
     *
     * @param increment amount to increment shots by
     */
    public void incrementShots(Integer increment) {
        this.shots += increment;
    }

    /**
     * Increments blocked shots by specified increment amount
     *
     * @param increment amount to increment blocked shots by
     */
    public void incrementBlockedShots(Integer increment) {
        this.blockedShots += increment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        SkaterSeason that = (SkaterSeason) o;
        return
                this.gamesPlayed.equals(that.gamesPlayed) &&
                        this.goals.equals(that.goals) &&
                        this.assists.equals(that.assists) &&
                        this.shots.equals(that.shots) &&
                        this.blockedShots.equals(that.blockedShots) &&
                        this.id.equals(that.id) &&
                        this.seasonString.equals(that.seasonString);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.seasonString, this.gamesPlayed, this.goals, this.assists, this.shots, this.blockedShots);
    }

    @Override
    public int compareTo(SkaterSeason season) {
        return this.seasonString.compareTo(season.getSeasonString());
    }
}
