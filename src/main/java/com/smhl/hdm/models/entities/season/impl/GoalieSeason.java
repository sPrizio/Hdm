package com.smhl.hdm.models.entities.season.impl;

import com.smhl.hdm.models.entities.season.Season;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

/**
 * Class implementation for a delineation of time representing a Goalie's perfomance over a period of time known as a season
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class GoalieSeason implements Season, Comparable<GoalieSeason> {

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
    private int gamesStarted;

    @Getter
    @Setter
    @Column
    @NonNull
    private int wins;

    @Getter
    @Setter
    @Column
    @NonNull
    private int losses;

    @Getter
    @Setter
    @Column
    @NonNull
    private int ties;

    @Getter
    @Setter
    @Column
    @NonNull
    private int saves;

    @Getter
    @Setter
    @Column
    @NonNull
    private int shotsAgainst;

    @Getter
    @Setter
    @Column
    @NonNull
    private int goalsAgainst;

    @Getter
    @Setter
    @Column
    @NonNull
    private int shutouts;


    //  METHODS

    /**
     * Increments games played by 1
     */
    public void incrementGamesPlayed() {
        this.gamesPlayed += 1;
    }

    /**
     * Increments games started by 1
     */
    public void incrementGamesStarted() {
        this.gamesStarted += 1;
    }

    /**
     * Increments wins by 1
     */
    public void incrementWins() {
        this.wins += 1;
    }

    /**
     * Increments losses by 1
     */
    public void incrementLosses() {
        this.losses += 1;
    }

    /**
     * Increments ties by 1
     */
    public void incrementTies() {
        this.ties += 1;
    }

    /**
     * Increments saves by the specified amount
     *
     * @param increment amount to increment by
     */
    public void incrementSaves(int increment) {
        this.saves += increment;
    }

    /**
     * Increments shots against by the specified amount
     *
     * @param increment amount to increment by
     */
    public void incrementShotsAgainst(int increment) {
        this.shotsAgainst += increment;
    }

    /**
     * Increments goals against by the specified amount
     *
     * @param increment amount to increment by
     */
    public void incrementGoalsAgainst(int increment) {
        this.goalsAgainst += increment;
    }

    /**
     * Increments shutouts by 1
     */
    public void incrementShutouts() {
        this.shutouts += 1;
    }

    /**
     * Gets the save percentage
     *
     * @return saves / shots against
     */
    public double getSavePercentage() {

        if (this.shotsAgainst == 0) {
            return 0.0;
        }

        return Math.round(((double) this.saves / (double) this.shotsAgainst) * 1000.0) / 1000.0;
    }

    /**
     * Gets the goals against average
     *
     * @return goals against per game
     */
    public double getGoalsAgainstAverage() {

        if (this.gamesPlayed == 0) {
            return 0.0;
        }

        return Math.round(((double) this.goalsAgainst / (double) this.gamesPlayed) * 100.0) / 100.0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        GoalieSeason that = (GoalieSeason) o;
        return
                gamesPlayed == that.gamesPlayed &&
                        gamesStarted == that.gamesStarted &&
                        wins == that.wins &&
                        losses == that.losses &&
                        ties == that.ties &&
                        saves == that.saves &&
                        shotsAgainst == that.shotsAgainst &&
                        goalsAgainst == that.goalsAgainst &&
                        shutouts == that.shutouts &&
                        id.equals(that.id) &&
                        seasonString.equals(that.seasonString);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, seasonString, gamesPlayed, gamesStarted, wins, losses, ties, saves, shotsAgainst, goalsAgainst, shutouts);
    }

    @Override
    public int compareTo(GoalieSeason goalieSeason) {
        return this.seasonString.compareTo(goalieSeason.getSeasonString());
    }
}