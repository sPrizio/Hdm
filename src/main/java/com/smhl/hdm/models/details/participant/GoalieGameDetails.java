package com.smhl.hdm.models.details.participant;

import com.smhl.hdm.models.details.Details;
import lombok.*;

import javax.persistence.*;

/**
 * A class meant to hold detailed information about a goalie's performance in a match
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class GoalieGameDetails implements Details {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Column
    @NonNull
    private boolean isStarter;

    @Getter
    @Setter
    @Column
    @NonNull
    private String gameResult;

    @Getter
    @Setter
    @Column
    @NonNull
    private int shotsAgainst;

    @Getter
    @Setter
    @Column
    @NonNull
    private int saves;

    @Getter
    @Setter
    @Column
    @NonNull
    private int goalsAgainst;
}
