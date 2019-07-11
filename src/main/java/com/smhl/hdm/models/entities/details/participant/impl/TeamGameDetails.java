package com.smhl.hdm.models.entities.details.participant.impl;

import com.smhl.hdm.models.entities.details.participant.ParticipantDetails;
import com.smhl.hdm.models.entities.participant.impl.Team;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * A class meant to hold detailed information about a team's performance in a match
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class TeamGameDetails implements ParticipantDetails<Team> {

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
    @JoinColumn(name = "team_id")
    @NonNull
    private Team participant;

    @Getter
    @Setter
    @Column
    @NonNull
    private String gameResult;

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
     * Increments goals for by 1
     */
    public void incrementGoalsFor() {
        this.goalsFor += 1;
    }

    /**
     * Increments goals against by 1
     */
    public void incrementGoalsAgainst() {
        this.goalsAgainst += 1;
    }
}
