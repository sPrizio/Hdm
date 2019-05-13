package com.smhl.hdm.models.game;

import com.smhl.hdm.models.HdmEntity;
import com.smhl.hdm.models.details.game.GameDetails;
import com.smhl.hdm.models.participant.impl.Team;
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
    @Column
    @NonNull
    private LocalDateTime gameTime;

    @Getter
    @Setter
    @Column
    @NonNull
    private String gameStatus;

    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(
            name = "home_team_game_relation",
            joinColumns = {@JoinColumn(name = "game_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "team_id", referencedColumnName = "id")}
    )
    @NonNull
    private Team homeTeam;

    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(
            name = "away_team_game_relation",
            joinColumns = {@JoinColumn(name = "game_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "team_id", referencedColumnName = "id")}
    )
    @NonNull
    private Team awayTeam;

    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(
            name = "game_details_relation",
            joinColumns = {@JoinColumn(name = "game_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "game_details_id", referencedColumnName = "id")}
    )
    private GameDetails gameDetails;
}
