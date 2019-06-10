package com.smhl.hdm.models.entities.details.game;

import com.smhl.hdm.models.entities.details.Details;
import com.smhl.hdm.models.entities.details.participant.GoalieGameDetails;
import com.smhl.hdm.models.entities.details.participant.SkaterGameDetails;
import com.smhl.hdm.models.entities.details.participant.TeamGameDetails;
import com.smhl.hdm.models.entities.game.ScoringPlay;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * A class meant to hold detailed information about a match
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class GameDetails implements Details {

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
    @Column
    @NonNull
    private int homeTeamScore;

    @Getter
    @Setter
    @Column
    @NonNull
    private int awayTeamScore;

    @Getter
    @Setter
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "game_details_id")
    @NonNull
    private Set<SkaterGameDetails> skaterGameDetails;

    @Getter
    @Setter
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "game_details_id")
    @NonNull
    private Set<GoalieGameDetails> goalieGameDetails;

    @Getter
    @Setter
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "game_details_id")
    @NonNull
    private Set<TeamGameDetails> teamGameDetails;

    @Getter
    @Setter
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "game_details_id")
    @NonNull
    private Set<ScoringPlay> scoringPlays;
}
