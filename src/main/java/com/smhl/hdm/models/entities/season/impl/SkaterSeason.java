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
    private int gamesPlayed;

    @Getter
    @Setter
    @Column
    @NonNull
    private int goals;

    @Getter
    @Setter
    @Column
    @NonNull
    private int assists;

    @Getter
    @Setter
    @Column
    @NonNull
    private int shots;

    @Getter
    @Setter
    @Column
    @NonNull
    private int blockedShots;


    //  METHODS

    /**
     * Returns a skater's points for the season
     *
     * @return goals summed with assists
     */
    public int getPoints() {
        return this.goals + this.assists;
    }

    /**
     * Returns the average number of points per game achieved for this season
     *
     * @return points divided by games played
     */
    public double getPointsPerGame() {
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
    public void incrementGoals(int increment) {
        this.goals += increment;
    }

    /**
     * Increments assists by specified increment amount
     *
     * @param increment amount to increment assists by
     */
    public void incrementAssists(int increment) {
        this.assists += increment;
    }

    /**
     * Increments shots by specified increment amount
     *
     * @param increment amount to increment shots by
     */
    public void incrementShots(int increment) {
        this.shots += increment;
    }

    /**
     * Increments blocked shots by specified increment amount
     *
     * @param increment amount to increment blocked shots by
     */
    public void incrementBlockedShots(int increment) {
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
                gamesPlayed == that.gamesPlayed &&
                        goals == that.goals &&
                        assists == that.assists &&
                        shots == that.shots &&
                        blockedShots == that.blockedShots &&
                        id.equals(that.id) &&
                        seasonString.equals(that.seasonString);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, seasonString, gamesPlayed, goals, assists, shots, blockedShots);
    }

    @Override
    public int compareTo(SkaterSeason season) {
        return this.seasonString.compareTo(season.getSeasonString());
    }
}
