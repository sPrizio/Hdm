package com.smhl.hdm.models.game;

import com.smhl.hdm.models.HdmEntity;
import com.smhl.hdm.models.details.GameDetails;
import com.smhl.hdm.models.participant.impl.Skater;
import com.smhl.hdm.models.participant.impl.Team;
import lombok.*;

import javax.persistence.*;

/**
 * Class representation of a scoring play in a hockey match
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class ScoringPlay implements HdmEntity {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_details_id", nullable = false)
    @NonNull
    private GameDetails gameDetails;

    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "team_id", referencedColumnName = "id")
    @NonNull
    private Team team;

    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "goal_scorer_id", referencedColumnName = "id")
    @NonNull
    private Skater goalScorer;

    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "primary_assisting_skater_id", referencedColumnName = "id")
    private Skater primaryAssistingSkater;

    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "secondary_assisting_skater_id", referencedColumnName = "id")
    private Skater secondaryAssistingSkater;
}
