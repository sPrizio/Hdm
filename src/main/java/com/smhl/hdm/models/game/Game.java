package com.smhl.hdm.models.game;

import com.smhl.hdm.models.HdmEntity;
import com.smhl.hdm.models.details.GameDetails;
import com.smhl.hdm.models.participant.impl.Team;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Class representation of a game, a match between 2 teams
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 */
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class Game implements HdmEntity, Comparable<Game> {

    @Getter
    @Id
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
    @JoinColumn(name = "home_team_id", referencedColumnName = "id")
    @NonNull
    private Team homeTeam;

    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "away_team_id", referencedColumnName = "id")
    @NonNull
    private Team awayTeam;

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
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "game_details_id", referencedColumnName = "id")
    @NonNull
    private GameDetails gameDetails;


    //  METHODS

    @Override
    public int compareTo(Game o) {
        return this.gameTime.compareTo(o.getGameTime());
    }
}
