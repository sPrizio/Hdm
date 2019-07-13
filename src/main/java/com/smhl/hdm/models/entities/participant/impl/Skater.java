package com.smhl.hdm.models.entities.participant.impl;

import com.smhl.hdm.models.entities.participant.Participant;
import com.smhl.hdm.models.entities.season.impl.SkaterSeason;
import com.smhl.hdm.utils.HdmUtils;
import lombok.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.util.Comparator;
import java.util.Optional;
import java.util.Set;

/**
 * An implementation of a participant who's purpose is to attack the opposing team's goalie
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class Skater implements Participant<SkaterSeason> {

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
    @NonNull
    private String position;

    @Getter
    @Setter
    @Column
    @NonNull
    private Boolean active;

    @Getter
    @Setter
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<SkaterSeason> seasons;


    //  METHODS

    @Override
    public SkaterSeason getCurrentSeason() {
        if (CollectionUtils.isNotEmpty(this.seasons)) {
            Optional<SkaterSeason> season = this.seasons
                    .stream()
                    .max(Comparator.comparing(SkaterSeason::getSeasonString));

            if (season.isPresent() && season.get().getSeasonString().equals(HdmUtils.getCurrentSeasonString())) {
                return season.get();
            }
        }

        return null;
    }

    @Override
    public SkaterSeason getSeasonForSeasonString(String seasonString) {
        if (CollectionUtils.isNotEmpty(this.seasons) && StringUtils.isNotEmpty(seasonString)) {
            Optional<SkaterSeason> season = this.seasons
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
    public void addSeason(SkaterSeason season) {

        if (season != null) {
            this.seasons.add(season);
        }
    }
}
