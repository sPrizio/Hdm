package com.smhl.hdm.models.season.impl;

import com.smhl.hdm.models.participant.impl.Skater;
import com.smhl.hdm.models.season.Season;
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

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "skater_id", nullable = false)
    @NonNull
    private Skater skater;


    //  METHODS

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
                seasonString.equals(that.seasonString) &&
                skater.getId().equals(that.getSkater().getId());
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
