package com.smhl.hdm.models.details;

import com.smhl.hdm.models.HdmEntity;
import com.smhl.hdm.models.game.Game;
import com.smhl.hdm.models.game.ScoringPlay;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

/**
 * A class meant to hold information about a Game
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class GameDetails implements HdmEntity {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "game_id", referencedColumnName = "id")
    @NonNull
    private Game game;

    @Getter
    @Setter
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "game_details")
    private Set<ScoringPlay> scoringPlays;

    @Getter
    @Setter
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "game_details")
    private Set<SkaterGameDetails> skaterGameDetails;

    @Getter
    @Setter
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "game_details")
    private Set<GoalieGameDetails> goalieGameDetails;

    @Getter
    @Setter
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "game_details")
    private Set<TeamGameDetails> teamGameDetails;
}
