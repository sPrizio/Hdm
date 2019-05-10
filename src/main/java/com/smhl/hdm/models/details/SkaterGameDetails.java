package com.smhl.hdm.models.details;

import com.smhl.hdm.models.HdmEntity;
import com.smhl.hdm.models.participant.impl.Skater;
import com.smhl.hdm.models.participant.impl.Team;
import lombok.*;

import javax.persistence.*;

/**
 * A class meant to hold information about a skater's performance in a Game
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class SkaterGameDetails implements HdmEntity {

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
    @JoinColumn(name = "skater_id", referencedColumnName = "id")
    @NonNull
    private Skater skater;

    @Getter
    @Setter
    @Column
    @NonNull
    private int goals;

    @Getter
    @Setter
    @Column
    @NonNull
    private int assists;

    @Getter
    @Setter
    @Column
    @NonNull
    private int shots;

    @Getter
    @Setter
    @Column
    @NonNull
    private int blockedShots;
}
