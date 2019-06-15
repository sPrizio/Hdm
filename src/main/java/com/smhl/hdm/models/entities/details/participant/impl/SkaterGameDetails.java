package com.smhl.hdm.models.entities.details.participant.impl;

import com.smhl.hdm.models.entities.details.participant.ParticipantDetails;
import com.smhl.hdm.models.entities.participant.impl.Skater;
import com.smhl.hdm.models.entities.participant.impl.Team;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * A class meant to hold detailed information about a skater's performance in a math
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class SkaterGameDetails implements ParticipantDetails<Skater> {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Column
    @NonNull
    private LocalDateTime gameTime;

    @Getter
    @Setter
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "skater_id")
    @NonNull
    private Skater participant;

    @Getter
    @Setter
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "team_id")
    @NonNull
    private Team team;

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


    //  METHODS

    /**
     * Increments goals by 1
     */
    public void incrementGoals() {
        this.goals += 1;
    }

    /**
     * Increments assists by 1
     */
    public void incrementAssists() {
        this.assists += 1;
    }

    /**
     * Increments shots by 1
     */
    public void incrementShots() {
        this.shots += 1;
    }

    /**
     * Increments blocked shots by 1
     */
    public void incrementBlockedShots() {
        this.blockedShots += 1;
    }
}
