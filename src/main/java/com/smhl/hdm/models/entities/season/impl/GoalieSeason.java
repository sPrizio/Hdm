package com.smhl.hdm.models.entities.season.impl;

import com.smhl.hdm.models.entities.season.Season;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

/**
 * Class implementation for a delineation of time representing a Goalie's performance over a period of time known as a season
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
    private Integer gamesPlayed;

    @Getter
    @Setter
    @Column
    @NonNull
    private Integer gamesStarted;

    @Getter
    @Setter
    @Column
    @NonNull
    private Integer wins;

    @Getter
    @Setter
    @Column
    @NonNull
    private Integer losses;

    @Getter
    @Setter
    @Column
    @NonNull
    private Integer ties;

    @Getter
    @Setter
    @Column
    @NonNull
    private Integer saves;

    @Getter
    @Setter
    @Column
    @NonNull
    private Integer shotsAgainst;

    @Getter
    @Setter
    @Column
    @NonNull
    private Integer goalsAgainst;

    @Getter
    @Setter
    @Column
    @NonNull
    private Integer shutouts;


    //  METHODS

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        GoalieSeason that = (GoalieSeason) o;
        return
                this.gamesPlayed.equals(that.gamesPlayed) &&
                        this.gamesStarted.equals(that.gamesStarted) &&
                        this.wins.equals(that.wins) &&
                        this.losses.equals(that.losses) &&
                        this.ties.equals(that.ties) &&
                        this.saves.equals(that.saves) &&
                        this.shotsAgainst.equals(that.shotsAgainst) &&
                        this.goalsAgainst.equals(that.goalsAgainst) &&
                        this.shutouts.equals(that.shutouts) &&
                        this.id.equals(that.id) &&
                        this.seasonString.equals(that.seasonString);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.seasonString, this.gamesPlayed, this.gamesStarted, this.wins, this.losses, this.ties, this.saves, this.shotsAgainst, this.goalsAgainst, this.shutouts);
    }

    @Override
    public int compareTo(GoalieSeason goalieSeason) {
        return this.seasonString.compareTo(goalieSeason.getSeasonString());
    }

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
    public void incrementSaves(Integer increment) {
        this.saves += increment;
    }

    /**
     * Increments shots against by the specified amount
     *
     * @param increment amount to increment by
     */
    public void incrementShotsAgainst(Integer increment) {
        this.shotsAgainst += increment;
    }

    /**
     * Increments goals against by the specified amount
     *
     * @param increment amount to increment by
     */
    public void incrementGoalsAgainst(Integer increment) {
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
    public Double getSavePercentage() {

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
    public Double getGoalsAgainstAverage() {

        if (this.gamesPlayed == 0) {
            return 0.0;
        }

        return Math.round(((double) this.goalsAgainst / (double) this.gamesPlayed) * 100.0) / 100.0;
    }
}
