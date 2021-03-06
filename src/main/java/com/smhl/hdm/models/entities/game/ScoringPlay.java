package com.smhl.hdm.models.entities.game;

import com.smhl.hdm.models.entities.HdmEntity;
import com.smhl.hdm.models.entities.participant.impl.Skater;
import com.smhl.hdm.models.entities.participant.impl.Team;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;

/**
 * A class representation of a scoring play in a game. A scoring play is a moment when a team
 * scores a goal on another team
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@Entity
@NoArgsConstructor
public class ScoringPlay implements HdmEntity {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "team_id")
    private Team team;

    @Getter
    @Setter
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "scoring_skater_id")
    @NonNull
    private Skater scoringSkater;

    @Getter
    @Setter
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "primary_assisting_skater_id")
    private Skater primaryAssistingSkater;

    @Getter
    @Setter
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "secondary_assisting_skater_id")
    private Skater secondaryAssistingSkater;


    /**
     * We write a custom constructor since we want to allow null values
     *
     * @param team                     team that scored
     * @param scoringSkater            the scorer
     * @param primaryAssistingSkater   the primary assisting player, can be null
     * @param secondaryAssistingSkater the secondary assisting player, can be null
     */
    public ScoringPlay(Team team, Skater scoringSkater, Skater primaryAssistingSkater, Skater secondaryAssistingSkater) {
        this.team = team;
        this.scoringSkater = scoringSkater;
        this.primaryAssistingSkater = primaryAssistingSkater;
        this.secondaryAssistingSkater = secondaryAssistingSkater;
    }
}
