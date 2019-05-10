package com.smhl.hdm.models.season.impl;

import com.smhl.hdm.models.participant.impl.Goalie;
import com.smhl.hdm.models.season.Season;
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

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goalie_id", nullable = false)
    @NonNull
    private Goalie goalie;


    //  METHODS

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
                seasonString.equals(that.seasonString) &&
                goalie.getId().equals(that.goalie.getId());
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
