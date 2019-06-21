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
    private Integer gamesPlayed;

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
    private Integer goalsFor;

    @Getter
    @Setter
    @Column
    @NonNull
    private Integer goalsAgainst;


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
    public void incrementGoalsFor(Integer increment) {
        this.goalsFor += increment;
    }

    /**
     * Increments goals against by a specified amount
     *
     * @param increment amount to increment by
     */
    public void incrementGoalsAgainst(Integer increment) {
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
                this.gamesPlayed.equals(that.gamesPlayed) &&
                        this.wins.equals(that.wins) &&
                        this.losses.equals(that.losses) &&
                        this.ties.equals(that.ties) &&
                        this.goalsFor.equals(that.goalsFor) &&
                        this.goalsAgainst.equals(that.goalsAgainst) &&
                        this.id.equals(that.id) &&
                        this.seasonString.equals(that.seasonString);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.seasonString, this.gamesPlayed, this.wins, this.losses, this.ties, this.goalsFor, this.goalsAgainst);
    }

    @Override
    public int compareTo(TeamSeason season) {
        return this.seasonString.compareTo(season.getSeasonString());
    }
}
