package com.smhl.hdm.models.entities.season.impl;

import com.smhl.hdm.models.entities.season.Season;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

/**
 * A class representation of a season, for a team
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class TeamSeason implements Season, Comparable<TeamSeason> {

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
    private int goalsFor;

    @Getter
    @Setter
    @Column
    @NonNull
    private int goalsAgainst;


    //  METHODS

    /**
     * Increments games played by 1
     */
    public void incrementGamesPlayed() {
        this.gamesPlayed += 1;
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
     * Increments goals for by a specified amount
     *
     * @param increment amount to increment by
     */
    public void incrementGoalsFor(int increment) {
        this.goalsFor += increment;
    }

    /**
     * Increments goals against by a specified amount
     *
     * @param increment amount to increment by
     */
    public void incrementGoalsAgainst(int increment) {
        this.goalsAgainst += increment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        TeamSeason that = (TeamSeason) o;
        return
                gamesPlayed == that.gamesPlayed &&
                        wins == that.wins &&
                        losses == that.losses &&
                        ties == that.ties &&
                        goalsFor == that.goalsFor &&
                        goalsAgainst == that.goalsAgainst &&
                        id.equals(that.id) &&
                        seasonString.equals(that.seasonString);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, seasonString, gamesPlayed, wins, losses, ties, goalsFor, goalsAgainst);
    }

    @Override
    public int compareTo(TeamSeason season) {
        return this.seasonString.compareTo(season.getSeasonString());
    }
}