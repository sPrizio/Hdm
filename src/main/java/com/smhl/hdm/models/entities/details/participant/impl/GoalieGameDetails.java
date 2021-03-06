package com.smhl.hdm.models.entities.details.participant.impl;

import com.smhl.hdm.models.entities.details.participant.ParticipantDetails;
import com.smhl.hdm.models.entities.participant.impl.Goalie;
import com.smhl.hdm.models.entities.participant.impl.Team;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * A class meant to hold detailed information about a goalie's performance in a match
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class GoalieGameDetails implements ParticipantDetails<Goalie> {

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
    @JoinColumn(name = "goalie_id")
    @NonNull
    private Goalie participant;

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
    private Boolean isStarter;

    @Getter
    @Setter
    @Column
    @NonNull
    private String gameResult;

    @Getter
    @Setter
    @Column
    @NonNull
    private Integer shotsAgainst;

    @Getter
    @Setter
    @Column
    @NonNull
    private Integer saves;

    @Getter
    @Setter
    @Column
    @NonNull
    private Integer goalsAgainst;


    //  METHODS

    /**
     * Marks the goalie as the starter for the game
     */
    public void markAsStarter() {
        this.isStarter = true;
    }

    /**
     * Increments shots against by 1
     */
    public void incrementShotsAgainst() {
        this.shotsAgainst += 1;
    }

    /**
     * Increments saves by 1
     */
    public void incrementSaves() {
        this.saves += 1;
    }

    /**
     * Increments goals against by 1
     */
    public void incrementGoalsAgainst() {
        this.goalsAgainst += 1;
    }
}
