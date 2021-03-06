package com.smhl.hdm.models.entities.participant.impl;

import com.smhl.hdm.enums.ParticipantPosition;
import com.smhl.hdm.models.entities.participant.Participant;
import com.smhl.hdm.models.entities.season.impl.GoalieSeason;
import com.smhl.hdm.utils.HdmUtils;
import lombok.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.util.Comparator;
import java.util.Optional;
import java.util.Set;

/**
 * An implementation of a participant who's purpose is to defend their team's net
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class Goalie implements Participant<GoalieSeason> {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Column
    @NonNull
    private String firstName;

    @Getter
    @Setter
    @Column
    @NonNull
    private String lastName;

    @Getter
    @Setter
    @Column
    private String position = ParticipantPosition.GOALIE.toString();

    @Getter
    @Setter
    @Column
    @NonNull
    private Boolean active;

    @Getter
    @Setter
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<GoalieSeason> seasons;


    //  METHODS

    @Override
    public GoalieSeason getCurrentSeason() {
        if (CollectionUtils.isNotEmpty(this.seasons)) {
            Optional<GoalieSeason> season = this.seasons
                    .stream()
                    .max(Comparator.comparing(GoalieSeason::getSeasonString));

            if (season.isPresent() && season.get().getSeasonString().equals(HdmUtils.getCurrentSeasonString())) {
                return season.get();
            }
        }

        return null;
    }

    @Override
    public GoalieSeason getSeasonForSeasonString(String seasonString) {
        if (CollectionUtils.isNotEmpty(this.seasons) && StringUtils.isNotEmpty(seasonString)) {
            Optional<GoalieSeason> season = this.seasons
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
    public String getName() {
        return this.firstName.trim() + " " + this.lastName.trim();
    }

    @Override
    public void addSeason(GoalieSeason season) {

        if (season != null) {
            this.seasons.add(season);
        }
    }
}
