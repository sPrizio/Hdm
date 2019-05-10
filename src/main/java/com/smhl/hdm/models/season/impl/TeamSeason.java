package com.smhl.hdm.models.season.impl;

import com.smhl.hdm.models.participant.impl.Team;
import com.smhl.hdm.models.season.Season;
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

    @Getter
    @Id
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

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", nullable = false)
    @NonNull
    private Team team;


    //  METHODS

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
                seasonString.equals(that.seasonString) &&
                team.getId().equals(that.team.getId());
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
