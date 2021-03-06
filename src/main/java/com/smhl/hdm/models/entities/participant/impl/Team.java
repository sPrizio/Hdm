package com.smhl.hdm.models.entities.participant.impl;

import com.smhl.hdm.models.entities.participant.Participant;
import com.smhl.hdm.models.entities.season.impl.TeamSeason;
import com.smhl.hdm.utils.HdmUtils;
import lombok.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.util.Comparator;
import java.util.Optional;
import java.util.Set;

/**
 * A team in this context refers to a collection of skaters and goalies representing a unified group
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
    @Column
    @NonNull
    private Boolean active;

    @Getter
    @Setter
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<TeamSeason> seasons;


    //  METHODS

    @Override
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

    @Override
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

    @Override
    public void addSeason(TeamSeason season) {

        if (season != null) {
            this.seasons.add(season);
        }
    }
}
