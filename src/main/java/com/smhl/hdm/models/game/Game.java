package com.smhl.hdm.models.game;

import com.smhl.hdm.models.HdmEntity;
import com.smhl.hdm.models.participant.impl.Team;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

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
}
