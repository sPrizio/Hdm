package com.smhl.hdm.models.details.participant;

import com.smhl.hdm.models.details.Details;
import com.smhl.hdm.models.participant.impl.Team;
import lombok.*;

import javax.persistence.*;

/**
 * A class meant to hold detailed information about a team's performance in a match
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class TeamGameDetails implements Details {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(
            name = "team_game_details_team_relation",
            joinColumns = {@JoinColumn(name = "team_game_details_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "team_id", referencedColumnName = "id")}
    )
    private Team team;

    @Getter
    @Setter
    @Column
    @NonNull
    private String gameStatus;

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


    //  METHODS

    /**
     * Increments goals for by 1
     */
    public void incrementGoalsFor() {
        this.goalsFor += 1;
    }

    /**
     * Increments goals against by 1
     */
    public void incrementGoalsAgainst() {
        this.goalsAgainst += 1;
    }
}
