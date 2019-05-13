package com.smhl.hdm.models.details.participant;

import com.smhl.hdm.models.details.Details;
import com.smhl.hdm.models.participant.impl.Skater;
import lombok.*;

import javax.persistence.*;

/**
 * A class meant to hold detailed information about a skater's performance in a math
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class SkaterGameDetails implements Details {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(
            name = "skater_game_details_skater_relation",
            joinColumns = {@JoinColumn(name = "skater_game_details_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "skater_id", referencedColumnName = "id")}
    )
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


    //  METHODS

    /**
     * Increments goals by 1
     */
    public void incrementGoals() {
        this.goals += 1;
    }

    /**
     * Increments assists by 1
     */
    public void incrementAssists() {
        this.assists += 1;
    }

    /**
     * Increments shots by 1
     */
    public void incrementShots() {
        this.shots += 1;
    }

    /**
     * Increments blocked shots by 1
     */
    public void incrementBlockedShots() {
        this.blockedShots += 1;
    }
}