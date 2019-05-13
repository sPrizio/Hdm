package com.smhl.hdm.models.participant.impl;

import com.smhl.hdm.models.participant.Participant;
import com.smhl.hdm.models.season.impl.TeamSeason;
import com.smhl.hdm.utils.HdmUtils;
import lombok.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.util.Comparator;
import java.util.Optional;
import java.util.Set;

/**
 * A team in this context refers to a collection of players representing a unified group sharing a common goal
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class Team implements Participant<TeamSeason> {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Column
    @NonNull
    private String name;

    @Getter
    @Setter
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "team")
    private Set<TeamSeason> seasons;


    //  METHODS

    /**
     * Returns the current season
     *
     * @return most recent skater season
     */
    public TeamSeason getCurrentSeason() {
        if (CollectionUtils.isNotEmpty(this.seasons)) {
            Optional<TeamSeason> season = this.seasons
                    .stream()
                    .max(Comparator.comparing(TeamSeason::getSeasonString));

            if (season.isPresent() && season.get().getSeasonString().equals(HdmUtils.getCurrentSeasonString())) {
                return season.get();
            }
        }

        return null;
    }

    /**
     * Returns the season matching the provided season string
     *
     * @param seasonString season string that we're looking for
     * @return returns season if found, null otherwise
     */
    public TeamSeason getSeasonForSeasonString(String seasonString) {
        if (CollectionUtils.isNotEmpty(this.seasons) && StringUtils.isNotEmpty(seasonString)) {
            Optional<TeamSeason> season = this.seasons
                    .stream()
                    .filter(s -> s.getSeasonString().equals(seasonString))
                    .findFirst();

            if (season.isPresent()) {
                return season.get();
            }
        }

        return null;
    }
}
