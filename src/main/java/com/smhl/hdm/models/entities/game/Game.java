package com.smhl.hdm.models.entities.game;

import com.smhl.hdm.models.entities.HdmEntity;
import com.smhl.hdm.models.entities.details.game.GameDetails;
import com.smhl.hdm.models.entities.participant.impl.Team;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Class representation of a match between participants, 2 teams
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class Game implements HdmEntity {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Column(unique = true)
    @NonNull
    private LocalDateTime gameTime;

    @Getter
    @Setter
    @Column
    @NonNull
    private String gameStatus;

    @Getter
    @Setter
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "home_team_id")
    @NonNull
    private Team homeTeam;

    @Getter
    @Setter
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "away_team_id")
    @NonNull
    private Team awayTeam;

    @Getter
    @Setter
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_details_id")
    private GameDetails gameDetails;
}
