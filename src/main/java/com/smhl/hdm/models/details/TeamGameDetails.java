package com.smhl.hdm.models.details;

import com.smhl.hdm.models.HdmEntity;
import com.smhl.hdm.models.participant.impl.Team;
import lombok.*;

import javax.persistence.*;

/**
 * A class meant to hold information about a team's performance in a Game
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class TeamGameDetails implements HdmEntity {

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
    @Column
    @NonNull
    private String gameResult;

    @Getter
    @Setter
    @Column
    @NonNull
    private int goalsFor;

    @Getter
    @Setter
    @Column
    @NonNull
    private int goalsAgainst;
}
